package com.himansu.resumeanalyzer.service;

import com.himansu.resumeanalyzer.entity.User;
import com.himansu.resumeanalyzer.repository.UserRepo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepo userRepo;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(
            UserRepo userRepo,
            BCryptPasswordEncoder passwordEncoder)
    {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(
            String fullName,
            String email,
            String password)
    {

        if (userRepo.existsByEmail(email))
        {
            throw new RuntimeException("Email already registered.");
        }

        User user = new User();

        user.setFullName(fullName);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));

        return userRepo.save(user);

    }

    public User loginUser(
            String email,
            String password)
    {

        User user = userRepo.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("Invalid Email"));

        if (!passwordEncoder.matches(password, user.getPassword()))
        {
            throw new RuntimeException("Invalid Password");
        }

        return user;

    }

}