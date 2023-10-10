package com.udemy.best_travel.infraestructure.services;

import com.udemy.best_travel.domain.repositories.mongo.AppUserRepository;
import com.udemy.best_travel.infraestructure.abstract_services.ModifyUserService;
import com.udemy.best_travel.util.exceptions.UsernameNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@AllArgsConstructor
public class AppUserService implements ModifyUserService {

    private static final String COLLECTION_NAME = "app_users";

    private final AppUserRepository appUserRepository;

    @Override
    public Map<String, Boolean> enabled(String username) {
        var user = this.appUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(COLLECTION_NAME));
        user.setEnabled(!user.isEnabled());
        var userSaved = this.appUserRepository.save(user);
        return Collections.singletonMap(userSaved.getUsername(), userSaved.isEnabled());
    }

    @Override
    public Map<String, List<String>> addRole(String username, String role) {
        return null;
    }

    @Override
    public Map<String, List<String>> removeRole(String username, String role) {
        return null;
    }
}
