package com.brave.braveapi.controller;

import com.brave.braveapi.dto.UserDTO;
import com.brave.braveapi.ports.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:8100")
public class BraveUserController {

    @Autowired
    private IUserService service;

    @GetMapping
    public ResponseEntity<UserDTO> findUser(
            @RequestParam(name = "login", required = true) String login,
            @RequestParam(name = "password", required = true) String password
    ) {
        return ResponseEntity.ok(service.find(login, password));
    }

    @PostMapping
    public ResponseEntity<UserDTO> insertUser(
            @RequestBody(required = true) UserDTO body
    ) {
        return ResponseEntity.ok(service.insert(body));
    }
}
