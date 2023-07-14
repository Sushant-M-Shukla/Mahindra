package com.mahindra.finance.service;

import com.mahindra.finance.dto.LoginRequest;
import com.mahindra.finance.entity.User;
import com.mahindra.finance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

@Service
public class UserService {

    private static final String SECRET_KEY = "yourSecretKey";

    @Autowired
    private UserRepository userRepository;

    public User registerUser(User userRequest) {
        return userRepository.save(userRequest);
    }

    public String generateAuthToken(Long id) {
        long expirationTimeMillis = System.currentTimeMillis() + 3600000;
        String token = Jwts.builder()
                .setSubject(id.toString())
                .setExpiration(new Date(expirationTimeMillis))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
        return token;
    }

    public User loginUser(LoginRequest userRequest) {
        return userRepository.findByEmailAndPassword(userRequest.getEmail(), userRequest.getPassword());
    }

    public Boolean checkUserExists(Long userId) {
        return userRepository.existsById(userId);
    }
}
