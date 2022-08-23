package com.solbegsoft.authapi.controllers;


import com.solbegsoft.authapi.models.requests.RegisterRequest;
import com.solbegsoft.authapi.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("auth-api/v1/test")
@RequiredArgsConstructor
public class TestController {

    private final UserService userService;

    /**
     * Endpoint for All user
     *
     * @return
     */
    @GetMapping("/all")
    public String getAll() {

        return "This message from Public Endpoint of Auth-api.";
    }

    /**
     * Endpoint for READER
     *
     * @return
     */
    @GetMapping("/r")
    public String getReaderUserApi() {
        return "READER API";
    }

    /**
     * Endpoint for WRITER
     *
     * @return
     */
    @GetMapping("/w")
    public String getWriterUserApi() {
        return "WRITER API";
    }


    @GetMapping("/s")
    public ResponseEntity getRegisterUser() {
        return ResponseEntity.ok("GET  only /s");
    }

    /**
     * Signup new user
     *
     * @param signupRequest signup model
     * @return String
     */
    @PostMapping("/s")
    public ResponseEntity<String> postRegisterUser(@RequestBody @Valid RegisterRequest signupRequest) {

        userService.registryUser(signupRequest);

        return ResponseEntity.ok("User CREATED");
    }
}
