package com.bezkoder.spring.datajpa.controller;

import com.bezkoder.spring.datajpa.dto.LoginRequest;
import com.bezkoder.spring.datajpa.dto.LoginResponse;
import com.bezkoder.spring.datajpa.model.Member;
import com.bezkoder.spring.datajpa.service.AuthService;
import com.bezkoder.spring.datajpa.service.JwtTokenProvider;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;

//@CrossOrigin(origins = "http://localhost:8081")
@CrossOrigin(origins = {"https://demo-heroku-ui-a3206905a154.herokuapp.com", "https://www.sjsudaipur.com"})
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            Member member = authService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
            // Generate JWT token
            String token = jwtTokenProvider.createToken(member.getMemberId().toString(), member.getRole());

            //return ResponseEntity.ok(new AuthResponse(token));
            return ResponseEntity.ok(new LoginResponse("Login successful", member.getRole(), member.getMemberId(), token));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }
}
