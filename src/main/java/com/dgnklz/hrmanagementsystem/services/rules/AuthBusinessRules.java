package com.dgnklz.hrmanagementsystem.services.rules;

import com.dgnklz.hrmanagementsystem.cores.exceptions.messages.ResponseMessage;
import com.dgnklz.hrmanagementsystem.models.securities.UserERole;
import com.dgnklz.hrmanagementsystem.models.securities.Userrole;
import com.dgnklz.hrmanagementsystem.repositories.UserRepository;
import com.dgnklz.hrmanagementsystem.repositories.UserRoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class AuthBusinessRules {
    UserRepository userRepository;
    UserRoleRepository userRoleRepository;

    public ResponseEntity<?> checkIfUserExistsByUsername(String username){
        if (userRepository.existsByUsername(username)) {
            return ResponseEntity.badRequest()
                    .body(new ResponseMessage("Username is already taken!"));
        }
        return null;
    }

    public ResponseEntity<?> checkIfUserExistsByEmail(String email){
        if (userRepository.existsByEmail(email)) {
            return ResponseEntity.badRequest()
                    .body(new ResponseMessage("Email is already in use!"));
        }
        return null;
    }

    public Set<Userrole> setUserRole(Set<String> strRoles){
        Set<Userrole> roles = new HashSet<>();
        if (strRoles == null) {
            Userrole userrole = userRoleRepository.findByName(UserERole.ROLE_MODERATOR);
            roles.add(userrole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Userrole adminRole = userRoleRepository.findByName(UserERole.ROLE_ADMIN);
                        roles.add(adminRole);
                        break;
                    default:
                        Userrole userRole = userRoleRepository.findByName(UserERole.ROLE_MODERATOR);
                        roles.add(userRole);
                }
            });
        }
        return roles;
    }

}
