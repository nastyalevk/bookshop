package com.nastya.bookShop.service.impl;

import com.nastya.bookShop.exception.CredentialsException;
import com.nastya.bookShop.model.request.LoginRequest;
import com.nastya.bookShop.model.request.SignUpRequest;
import com.nastya.bookShop.model.response.JwtResponse;
import com.nastya.bookShop.model.response.MessageResponse;
import com.nastya.bookShop.model.role.ERole;
import com.nastya.bookShop.model.role.RoleDto;
import com.nastya.bookShop.model.user.UserDto;
import com.nastya.bookShop.model.userRoles.UserRolesDto;
import com.nastya.bookShop.security.jwt.JwtUtils;
import com.nastya.bookShop.security.services.UserDetailsImpl;
import com.nastya.bookShop.service.api.AuthService;
import com.nastya.bookShop.service.api.EmailService;
import com.nastya.bookShop.service.api.UserRoleService;
import com.nastya.bookShop.service.api.UserService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;
    private final UserService userService;
    private final UserRoleService userRoleService;
    private final EmailService emailService;
    private static final String emailRegex = "^(.+)@(.+)$";
    private static final String passwordRegex = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";

    @Autowired
    public AuthServiceImpl(AuthenticationManager authenticationManager, PasswordEncoder encoder,
                           JwtUtils jwtUtils, UserService userService, UserRoleService userRoleService,
                           EmailService emailService) {
        this.authenticationManager = authenticationManager;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
        this.userService = userService;
        this.userRoleService = userRoleService;
        this.emailService = emailService;
    }

    @Override
    @Transactional
    public ResponseEntity<?> authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        JwtResponse jwtResponse = new JwtResponse();
        jwtResponse.setToken(jwt);
        jwtResponse.setUsername(userDetails.getUsername());
        jwtResponse.setEmail(userDetails.getEmail());
        jwtResponse.setRoles(roles);
        jwtResponse.setActivated(userDetails.isAccountNonLocked());
        jwtResponse.setIsEnabled(userDetails.isEnabled());
        return ResponseEntity.ok(jwtResponse);
    }

    @Override
    @Transactional
    public ResponseEntity<MessageResponse> registerUser(SignUpRequest signUpRequest)
            throws UnsupportedEncodingException, MessagingException, CredentialsException {
        UserDto user = createUser(signUpRequest);
        credentialsValidate(user.getEmail(), signUpRequest.getPassword());
        user.setPassword(encoder.encode(signUpRequest.getPassword()));
        user.setIsEnabled(false);
        userService.saveUser(user);
        generateUserRolesDto(user, generateRoles(signUpRequest.getRole()));
        emailService.sendVerificationEmail(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));

    }

    @Override
    @Transactional
    public ResponseEntity<?> newUserByAdmin(SignUpRequest signUpRequest)
            throws UnsupportedEncodingException, MessagingException {
        UserDto user = createUser(signUpRequest);
        String randomCode = RandomString.make(20);
        credentialsValidate(user.getEmail(), randomCode);
        user.setPassword(encoder.encode(randomCode));
        user.setIsEnabled(true);
        userService.saveUser(user);
        generateUserRolesDto(user, generateRoles(signUpRequest.getRole()));
        emailService.sendCreation(user, randomCode);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    private void repeatCheck(SignUpRequest signUpRequest) {
        if (userService.existsByUserName(signUpRequest.getUsername())) {
            throw new CredentialsException("Error: Username is already taken!");
        }
        if (userService.existsByEmail(signUpRequest.getEmail())) {
            throw new CredentialsException("Error: Email is already in use!");
        }
    }

    private void credentialsValidate(String email, String password) {
        if (!email.matches(emailRegex)) {
            throw new CredentialsException("Invalid email!");
        }
        if (!password.matches(passwordRegex)) {
            throw new CredentialsException("Password is too easy!\n" +
                    "Minimum eight characters, at least one letter and one number.");
        }
    }

    private UserDto createUser(SignUpRequest signUpRequest) throws CredentialsException {
        repeatCheck(signUpRequest);
        UserDto user = new UserDto();
        user.setUsername(signUpRequest.getUsername());
        user.setEmail(signUpRequest.getEmail());
        if (signUpRequest.getActivated() == null) {
            user.setActivated(true);
        } else {
            user.setActivated(signUpRequest.getActivated());
        }
        return user;
    }

    private Set<RoleDto> generateRoles(Set<String> strRoles) {
        Set<RoleDto> roles = new HashSet<>();
        if (strRoles == null) {
            RoleDto userRole = new RoleDto();
            userRole.setName(ERole.ROLE_CLIENT);
            userRole.setId(3);
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                RoleDto userRole = new RoleDto();
                switch (role) {
                    case "ROLE_ADMIN":
                        userRole.setName(ERole.ROLE_ADMIN);
                        userRole.setId(1);
                        roles.add(userRole);
                        break;
                    case "ROLE_OWNER":
                        userRole.setName(ERole.ROLE_OWNER);
                        userRole.setId(2);
                        roles.add(userRole);
                        break;
                    case "ROLE_CLIENT":
                        userRole = new RoleDto();
                        userRole.setId(3);
                        userRole.setName(ERole.ROLE_CLIENT);
                        roles.add(userRole);
                        break;
                }
            });
        }
        return roles;
    }

    private void generateUserRolesDto(UserDto user, Set<RoleDto> roles) {
        UserRolesDto userRolesDto = new UserRolesDto();
        for (RoleDto i : roles) {
            userRolesDto.setUsername(user.getUsername());
            userRolesDto.setRoleId(i.getId());
            userRoleService.save(userRolesDto);
        }
    }
}
