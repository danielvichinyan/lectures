package com.knowit.lectures.services.users;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.knowit.lectures.domain.entities.ERole;
import com.knowit.lectures.domain.entities.Role;
import com.knowit.lectures.domain.entities.User;
import com.knowit.lectures.exceptions.UserDoesNotExist;
import com.knowit.lectures.repository.RoleRepository;
import com.knowit.lectures.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Bean
    public Function<?, ?> receiveUserAndRole() {
        return x -> {
            if (x == null) {
                return null;
            }
            this.saveUserAndRole(x.toString());
            return "";
        };
    }

    private void saveUserAndRole(String jsonString) {
        JsonObject jsonObject = new JsonParser().parse(jsonString).getAsJsonObject();
        User user = new User();
        user.setId(jsonObject.get("userId").getAsString());
        Set<Role> roles = new HashSet<>();

        if (this.roleRepository.count() == 0) {
            Role roleE = new Role();
            Role roleA = new Role();
            Role roleU = new Role();

            if (!jsonObject.get("roleEDITOR").isJsonNull()) {
                roleE.setId(jsonObject.get("roleEDITORId").getAsString());
                roleE.setName(ERole.EDITOR);
                this.roleRepository.saveAndFlush(roleE);
            }

            if (!jsonObject.get("roleADMIN").isJsonNull()) {
                roleA.setId(jsonObject.get("roleADMINId").getAsString());
                roleA.setName(ERole.ADMIN);
                this.roleRepository.saveAndFlush(roleA);
            }

            if (!jsonObject.get("roleUSER").isJsonNull()) {
                roleU.setId(jsonObject.get("roleUSERId").getAsString());
                roleU.setName(ERole.USER);
                this.roleRepository.saveAndFlush(roleU);
            }
        }

        if (this.userRepository.count() == 0) {
            Role roleE = this.roleRepository.findByName(ERole.EDITOR);
            Role roleA = this.roleRepository.findByName(ERole.ADMIN);
            Role roleU = this.roleRepository.findByName(ERole.USER);
            roles.add(roleE);
            roles.add(roleA);
            roles.add(roleU);
            user.setRoles(roles);
        } else if (this.userRepository.count() == 1) {
            Role roleA = this.roleRepository.findByName(ERole.ADMIN);
            Role roleU = this.roleRepository.findByName(ERole.USER);
            roles.add(roleA);
            roles.add(roleU);
            user.setRoles(roles);
        } else {
            Role roleU = this.roleRepository.findByName(ERole.USER);
            roles.add(roleU);
            user.setRoles(roles);
        }

        this.userRepository.saveAndFlush(user);
    }

    @Override
    public User fetchByUserId(String userId) throws UserDoesNotExist {
        return this.userRepository.findById(userId).orElseThrow(() -> new UserDoesNotExist());
    }
}
