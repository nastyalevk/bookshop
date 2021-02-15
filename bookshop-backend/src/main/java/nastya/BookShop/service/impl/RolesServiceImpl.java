package nastya.BookShop.service.impl;

import nastya.BookShop.dto.role.ERole;
import nastya.BookShop.dto.role.RoleDto;
import nastya.BookShop.model.Role;
import nastya.BookShop.repository.RolesRepository;
import nastya.BookShop.service.api.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class RolesServiceImpl implements RolesService {

    private final RolesRepository rolesRepository;

    @Autowired
    public RolesServiceImpl(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    @Override
    public void saveRole(RoleDto roleDto) {
        rolesRepository.save(transfer(roleDto));
    }

    @Override
    public List<RoleDto> findAll() {
        List<Role> roles = rolesRepository.findAll();
        List<RoleDto> roleDtos = new ArrayList<>();
        for (Role i : roles) {
            roleDtos.add(transfer(i));
        }
        return roleDtos;
    }

    @Override
    public List<Role> findByName(String name) {
        return rolesRepository.findByRoleName(ERole.valueOf(name));
    }

    private RoleDto transfer(Role role) {
        return new RoleDto(role.getId(), role.getRoleName());
    }

    private Role transfer(RoleDto roleDto) {
        return new Role(roleDto.getId(), roleDto.getName().toString());
    }

}
