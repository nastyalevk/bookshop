package com.nastya.bookShop.service.api;

import com.nastya.bookShop.model.role.RoleDto;

import java.util.List;

public interface RoleService {

    List<RoleDto> findByName(String name);

}
