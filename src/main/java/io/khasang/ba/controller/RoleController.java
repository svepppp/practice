package io.khasang.ba.controller;

import io.khasang.ba.entity.Role;
import io.khasang.ba.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for REST layer of role management: provided POST, GET, PUT and DELETE functionality
 */
@Controller
@RequestMapping(value = "/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Role addRole(@RequestBody Role newRole) {
        roleService.addRole(newRole);
        return newRole;
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Role getRoleById(@PathVariable(value = "id") long id) {
        return roleService.getRoleById(id);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Role updateRole(@RequestBody Role updatedRole) {
        return roleService.updateRole(updatedRole);
    }

    @RequestMapping(value = "/get/all", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Role deleteRole(@PathVariable(value = "id") long id) {
        return roleService.deleteRole(id);
    }
}
