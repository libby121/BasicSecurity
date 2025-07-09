package com.example.basicSecurity.controller;

import com.example.basicSecurity.model.UserDTO;
import com.example.basicSecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/login")
    public ResponseEntity<String>login(@RequestBody UserDTO u){

           return ResponseEntity.ok(
                   service.verify(u));
    }

    @PostMapping("/register")
    public ResponseEntity<?> getOneUser(@RequestBody UserDTO dt){
        return ResponseEntity.ok(service.register(dt));
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getOneUser(@PathVariable int id){
        try{
            return ResponseEntity.ok(service.getUser(id));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("???");
        }
    }

    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody List<UserDTO>users){
              service.addUsers(users);
            return ResponseEntity.ok("yeyyy");


    }






}
