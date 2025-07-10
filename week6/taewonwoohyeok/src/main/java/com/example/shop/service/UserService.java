package com.example.shop.service;

import com.example.shop.dto.UserRequest;
import com.example.shop.entity.User;
import com.example.shop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Long createUser(UserRequest userRequest) {
        User user = userRequest.toEntity();
        return userRepository.save(user).getUserId();
    }


}
