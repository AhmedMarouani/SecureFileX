package com.example.Challenge2.services;

import com.example.Challenge2.entities.Role;
import com.example.Challenge2.repositories.RoleRepository;
import com.example.Challenge2.responses.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void verifyExistenceById(Integer id){
        roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("no employee was found for id " + id));
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRoleById(Integer id) {
        return roleRepository.findById(id).get();
    }

    @Override
    public Role addRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role editRole(Integer id, Role role) {
        Role editedRole = roleRepository.findById(id).get();
        editedRole.setName(role.getName());
        editedRole.setCreatedAt(role.getCreatedAt());
        editedRole.setUpdatedAt(role.getUpdatedAt());


        return roleRepository.save(editedRole);
    }

    @Override
    public ResponseEntity<MessageResponse> deleteRoleById(Integer id) {
        verifyExistenceById(id);
        roleRepository.deleteById(id);
        MessageResponse messageResponse = new MessageResponse("Role Deleted!");
        return ResponseEntity.ok(messageResponse);
    }
}
