package com.example.Challenge2.controllers;

import com.example.Challenge2.entities.Role;
import com.example.Challenge2.responses.MessageResponse;
import com.example.Challenge2.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/Roles")

public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/")
    public List<Role> getAllRoles(){
        List<Role> Roles =  roleService.getAllRoles();
        return Roles;
    }
    @GetMapping("/{id}")
    public Role getRoleByID(@PathVariable("id") Integer id){
        Role role = roleService.getRoleById(id);
        return role;
    }
    @PostMapping("/addRole")
    public Role addNewRole(@RequestBody Role role){
        Role newRole = roleService.addRole(role);
        return newRole;
    }
    @PutMapping("/editRole/{id}")
    public Role updateRoleByID(@PathVariable("id") Integer id, @RequestBody Role role){
        Role editedRole = roleService.editRole(id, role);
        return editedRole;
    }
    @DeleteMapping("/deleteRole/{id}")
    public ResponseEntity<MessageResponse> deleteRoleById(@PathVariable("id") Integer id){
        ResponseEntity<MessageResponse> response = roleService.deleteRoleById(id);
        return response;
    }
}
