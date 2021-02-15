package nastya.BookShop.service.impl;

import nastya.BookShop.dto.userRoles.UserRolesDto;
import nastya.BookShop.model.UserRoles;
import nastya.BookShop.model.UserRolesId;
import nastya.BookShop.repository.RolesRepository;
import nastya.BookShop.repository.UserRepository;
import nastya.BookShop.repository.UserRolesRepository;
import nastya.BookShop.service.api.UserRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class UserRolesServiceImpl implements UserRolesService {

    private final UserRolesRepository userRolesRepository;
    private final UserRepository userRepository;
    private final RolesRepository rolesRepository;

    @Autowired
    public UserRolesServiceImpl(UserRolesRepository userRolesRepository, UserRepository userRepository,
                                RolesRepository rolesRepository) {
        this.userRolesRepository = userRolesRepository;
        this.userRepository = userRepository;
        this.rolesRepository = rolesRepository;
    }

    @Override
    public UserRoles saveUserRole(UserRolesDto userRolesDto) {
        return userRolesRepository.save(transfer(userRolesDto));
    }

    @Override
    public List<UserRolesDto> findAll() {
        List<UserRoles> userRoles = userRolesRepository.findAll();
        List<UserRolesDto> userRolesDtos = new ArrayList<>();
        for (UserRoles i : userRoles) {
            userRolesDtos.add(transfer(i));
        }
        return userRolesDtos;
    }

    @Override
    public UserRolesDto findById(Integer id) {
        return transfer(userRolesRepository.getOne(id));
    }

    private UserRolesDto transfer(UserRoles userRoles) {
        UserRolesDto userRolesDto = new UserRolesDto();
        userRolesDto.setUsername(userRoles.getUserRolesId().getUser().getUsername());
        userRolesDto.setRoleId(userRoles.getUserRolesId().getRole().getId());
        return userRolesDto;
    }

    private UserRoles transfer(UserRolesDto userRolesDto) {
        UserRoles userRoles = new UserRoles();
        userRoles.setUserRolesId(new UserRolesId(userRepository.findByUsername(userRolesDto.getUsername()),
                rolesRepository.getOne(userRolesDto.getRoleId())));
        return userRoles;
    }
}
