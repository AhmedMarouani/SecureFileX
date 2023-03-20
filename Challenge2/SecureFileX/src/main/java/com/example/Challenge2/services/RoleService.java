package com.example.Challenge2.services;

import com.example.Challenge2.entities.Role;
import com.example.Challenge2.responses.MessageResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();

    Role getRoleById(Integer id);

    Role addRole(Role role);

    Role editRole(Integer id, Role role);

    ResponseEntity<MessageResponse> deleteRoleById(Integer id);

    void verifyExistenceById(Integer id);

}
