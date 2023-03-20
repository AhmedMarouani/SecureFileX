package com.example.Challenge2.services;

import com.example.Challenge2.entities.*;
import com.example.Challenge2.repositories.UserRepository;
import com.example.Challenge2.responses.AuthenticationResponse;
import com.example.Challenge2.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    private final SendMailService sendMailService;
    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .age(request.getAge())
                .createdAt(request.getCreatedAt())
                .updatedAt(request.getUpdatedAt())
                .password(passwordEncoder.encode(request.getPassword()))
                .erole(ERole.USER)
                .build();
                if(!userRepository.existsByEmail(request.getEmail())){
                    userRepository.save(user);
                    var jwtToken = jwtService.generateToken(user);
                    EmailRequest emailRequest = new EmailRequest();
                    emailRequest.setTo(user.getEmail());
                    emailRequest.setSubject("Registration successful");
                    emailRequest.setText("Dear " + user.getFirstName() + ",\n\nThank you for registering with us.");
                    sendMailService.sendTextEmail(emailRequest);
                    return AuthenticationResponse.builder().token(jwtToken).build();
                }else{
                    throw new RuntimeException("Email already exists");
                }

    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );//get user
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);//generate token
        return AuthenticationResponse.builder().token(jwtToken).build();
    }


}
