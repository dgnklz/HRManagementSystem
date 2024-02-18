package com.dgnklz.hrmanagementsystem.services.rules;

import com.dgnklz.hrmanagementsystem.cores.exceptions.types.BusinessException;
import com.dgnklz.hrmanagementsystem.models.entities.Role;
import com.dgnklz.hrmanagementsystem.repositories.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoleBusinessRule {
    private RoleRepository roleRepository;

    public void checkIfRoleExistsByName(String name) {
        List<Role> roles = roleRepository.findAll();
        for (Role role : roles){
            if (role.getName().equalsIgnoreCase(name)){
                throw new BusinessException("Role exist by name: " + name);
            }
        }
    }

    public void checkIfRoleExistsByName(String name, int id){
        Role role = roleRepository.findById(id).orElseThrow();
        if (!name.equalsIgnoreCase(role.getName())){
            checkIfRoleExistsByName(name);
        }
    }

    public void checkIfRoleNotExistsByName(String name) {
        if (!roleRepository.existsRoleByName(name)) {
            throw new BusinessException("Role does not exist");
        }
    }

    public void checkIfRoleNotExistById(int id){
        if(!roleRepository.existsById(id)){
            throw new BusinessException("Role is not exist by id: " + id);
        }
    }

    public Role getRoleById(int id){
        Role role = roleRepository.findById(id).orElseThrow();
        return  role;
    }



}
