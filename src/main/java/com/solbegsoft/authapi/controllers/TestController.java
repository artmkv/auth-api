package com.solbegsoft.authapi.controllers;


import com.solbegsoft.authapi.models.entities.ERole;
import com.solbegsoft.authapi.models.entities.Role;
import com.solbegsoft.authapi.models.entities.User;
import com.solbegsoft.authapi.models.requests.RegisterRequest;
import com.solbegsoft.authapi.repositories.RoleRepository;
import com.solbegsoft.authapi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("auth-api/v1/test")
@RequiredArgsConstructor
public class TestController {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    /**
     * Endpoint for All user
     * @return
     */
    @GetMapping("/all")
    public String getAll(){

        return "This message from Public Endpoint of Auth-api.";
    }

    /**
     * Endpoint for READER
     * @return
     */
    @GetMapping("/r")
    public String getReaderUserApi(){
        return "READER API";
    }

    /**
     * Endpoint for WRITER
     * @return
     */
    @GetMapping("/w")
    public String getWriterUserApi(){
        return "WRITER API";
    }


    @GetMapping("/s")
    public ResponseEntity getRegisterUser() {
    return ResponseEntity.ok("GET  only /s");
    }

    /**
     * Signup new user
     * @param signupRequest
     * @return
     */
    @PostMapping("/s")
    public ResponseEntity<?> postRegisterUser(@RequestBody RegisterRequest signupRequest) {

        // TODO: 30.07.2022 Это полотно валидации адище, юзай validation https://www.baeldung.com/javax-validation
        // понятно, что тестовый контроллер и т.п., но значительно быстрее кинуть пару анноташек, чем писать всё это
        // а что если в RegisterRequest username null? или email? Все тела запросов должны валидироваться

        // TODO: 30.07.2022 странная проверка, у нас не может быть 3 условных Johna ? Поправь пожалуйста и в ентити, там тоже стоит ограничение(уникальность)
        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(("Error: Username is exist"));
        }

        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(("Error: Email is exist"));
        }

        User user = User.builder()
                .username(signupRequest.getUsername())
                .email(signupRequest.getEmail())
                .password(passwordEncoder.encode(signupRequest.getPassword()))
                .build();

        Set<String> reqRoles = signupRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if (reqRoles == null) {
            String str = ERole.ROLE_READER.toString();
            Role userRole = roleRepository
                    .findByName(ERole.ROLE_READER.name())
                    .orElseThrow(() -> new RuntimeException("Error, Role READER is not found"));
            roles.add(userRole);
        } else {
            reqRoles.forEach(r -> {
                // TODO: 30.07.2022 switch не очень хорошо использовать, имеет свои недостатки, либо if/else, либо паттерн стратегия
                // нарушен принцип DRY - у тебя повтояряется код, разница только в том, что меняется текст и роль
                switch (r) { // TODO: 30.07.2022 а что такое "r" ? имена переменных должны быть именами, а не символами
                    case "WRITER":
                        Role wrRole = roleRepository
                                .findByName(ERole.ROLE_WRITER.name())
                                .orElseThrow(() -> new RuntimeException("Error, Role WRITER is not found"));
                        roles.add(wrRole);

                    default:
                        Role rdRole = roleRepository
                                .findByName(ERole.ROLE_READER.name())
                                .orElseThrow(() -> new RuntimeException("Error, Role USER is not found"));
                        roles.add(rdRole);
                }
            });
        }
        user.setRoles(roles);
        userRepository.save(user);
        return ResponseEntity.ok("User CREATED");
    }
}
