package nastya.BookShop.service.impl;

import nastya.BookShop.dto.logs.ELogs;
import nastya.BookShop.dto.role.ERole;
import nastya.BookShop.dto.role.RoleDto;
import nastya.BookShop.dto.user.UserDto;
import nastya.BookShop.model.Logs;
import nastya.BookShop.model.Role;
import nastya.BookShop.model.User;
import nastya.BookShop.model.UserRoles;
import nastya.BookShop.model.UserRolesId;
import nastya.BookShop.repository.LogsRepository;
import nastya.BookShop.repository.RolesRepository;
import nastya.BookShop.repository.UserRepository;
import nastya.BookShop.repository.UserRolesRepository;
import nastya.BookShop.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRolesRepository userRolesRepository;
    private final RolesRepository rolesRepository;
    private final LogsRepository logsRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserRolesRepository userRolesRepository,
                           RolesRepository rolesRepository, LogsRepository logsRepository) {
        this.userRepository = userRepository;
        this.userRolesRepository = userRolesRepository;
        this.rolesRepository = rolesRepository;
        this.logsRepository = logsRepository;
    }

    @Override
    public UserDto findById(Integer id) {
        return transfer(userRepository.getOne(id));
    }


    @Override
    public List<UserDto> findAll() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        for (User i : users) {
            userDtos.add(transfer(i));
        }
        return userDtos;
    }


    @Override
    public void saveUser(UserDto userDto) {
        createUser(userDto);
    }

    @Override
    public void updateUser(UserDto userDto, String username) {
        if (userRepository.getOne(userDto.getId()).getActivated() != userDto.getActivated()) {
            Logs log = new Logs();
            log.setUser(userRepository.getOne(userDto.getId()));
            log.setAdmin(userRepository.findByUsername(username));
            log.setDatetime(new Date());
            if (userDto.getActivated()) {
                log.setOperation(ELogs.UNBLOCK.toString());
            } else {
                log.setOperation(ELogs.BLOCK.toString());
            }
            log.setReason(userDto.getMessage());
            logsRepository.save(log);
        }
        createUser(userDto);
    }


    @Override
    public UserDto findByUsername(String userName) {
        return transfer(userRepository.findByUsername(userName));
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public void updateUserRoles(String[] roles, Integer userId, String adminUsername, String message) {
        for (String i : roles) {
            String[] split = i.split("-");
            if ("create".equalsIgnoreCase(split[1])) {
                userRolesRepository.save(newUserRole(split[0], userId));
            } else if ("delete".equalsIgnoreCase(split[1])) {
                userRolesRepository.delete(newUserRole(split[0], userId));
            }
        }
        Logs log = new Logs();
        log.setUser(userRepository.getOne(userId));
        log.setAdmin(userRepository.findByUsername(adminUsername));
        log.setDatetime(new Date());
        log.setOperation(ELogs.CHANGE_ROLE.toString());
        log.setReason(message.replace("_", " "));
        logsRepository.save(log);
    }

    private UserRoles newUserRole(String roleName, Integer id) {
        User user = userRepository.getOne(id);
        Role role = rolesRepository.getRoleByRoleName(roleName);
        UserRolesId userRolesId = new UserRolesId();
        userRolesId.setRole(role);
        userRolesId.setUser(user);
        UserRoles userRoles = new UserRoles();
        userRoles.setUserRolesId(userRolesId);
        return userRoles;
    }

    private UserDto transfer(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setFirstName(user.getFirstName());
        userDto.setEmail(user.getEmail());
        userDto.setLastName(user.getLastName());
        userDto.setPassword(user.getPassword());
        List<UserRoles> userRoles = userRolesRepository.findUserRolesByUserRolesIdUser(user);
        Set<RoleDto> rolesDto = new HashSet<>();
        for (UserRoles i : userRoles) {
            RoleDto roleDto = new RoleDto();
            roleDto.setName(ERole.valueOf(i.getUserRolesId().getRole().getRoleName()));
            roleDto.setId(i.getUserRolesId().getRole().getId());
            rolesDto.add(roleDto);
        }
        userDto.setRoles(rolesDto);
        userDto.setActivated(user.getActivated());
        userDto.setIsEnabled(user.getIsEnabled());
        return userDto;
    }

    private User transfer(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setUsername(userDto.getUsername());
        user.setFirstName(userDto.getFirstName());
        user.setEmail(userDto.getEmail());
        user.setLastName(userDto.getLastName());
        user.setPassword(userDto.getPassword());
        user.setActivated(userDto.getActivated());
        user.setIsEnabled(userDto.getIsEnabled());
        return user;
    }

    private void createUser(UserDto userDto) {
        userRepository.save(transfer(userDto));
    }
}
