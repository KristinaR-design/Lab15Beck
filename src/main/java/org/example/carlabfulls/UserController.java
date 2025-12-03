package org.example.carlabfulls;


import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepository,
                          PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody LoginRequest request) {

        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("error", "Username already exists"));
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);

        return ResponseEntity.status(201)
                .body(Map.of("message", "User registered successfully"));
    }



    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        return userRepository.findByUsername(request.getUsername())
                .filter(user -> passwordEncoder.matches(
                        request.getPassword(),
                        user.getPassword()
                ))
                .map(user -> {

                    String token = "dummy-token-" + user.getUsername();
                    return ResponseEntity.ok(Map.of("token", token));
                })
                .orElseGet(() ->
                        ResponseEntity.status(401)
                                .body(Map.of("error", "Invalid username or password"))
                );
    }
}

