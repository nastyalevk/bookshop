package nastya.BookShop.controller;

import nastya.BookShop.dto.role.RoleDto;
import nastya.BookShop.model.Role;
import nastya.BookShop.service.api.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    private final RolesService roleService;

    @Autowired
    public RoleController(RolesService roleService) {
        this.roleService = roleService;
    }

    @GetMapping()
    public ResponseEntity<List<RoleDto>> findAll() {
        return new ResponseEntity<>(roleService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> saveRole(@RequestBody RoleDto roleDto) {
        roleService.saveRole(roleDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{role}")
    public ResponseEntity<List<Role>> findByName(@PathVariable("role") String name) {
        return new ResponseEntity<>(roleService.findByName(name), HttpStatus.OK);
    }


}
