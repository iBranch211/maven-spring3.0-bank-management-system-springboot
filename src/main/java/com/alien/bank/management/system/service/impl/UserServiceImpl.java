package com.alien.bank.management.system.service.impl;

import com.alien.bank.management.system.entity.User;
import com.alien.bank.management.system.model.authentication.UserProfileResponseModel;
import com.alien.bank.management.system.repository.UserRepository;
import com.alien.bank.management.system.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserProfileResponseModel getUserProfile() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("email:" + email);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User " + email + " Not Found"));

        return UserProfileResponseModel
                .builder()
                .name(user.getName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .build();
    }
}