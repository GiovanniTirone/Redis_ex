package com.example.redis_ex.controllers;


import com.example.redis_ex.entities.jpa.UserJPA;
import com.example.redis_ex.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping()
    public UserJPA create (@RequestBody UserJPA user) {
        return userService.create(user);
    }

    @GetMapping
    public List<UserJPA> readAll ( ) {
        return userService.readAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserJPA> readById (@PathVariable  Long id) {
        UserJPA userJPA = userService.readById(id);
        if(userJPA == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        else{
            return ResponseEntity.status(HttpStatus.OK).body(userJPA);
        }
    }

    @GetMapping("/{id}/fast")
    public void readByIdFast (@PathVariable  Long id) {
        userService.readByIdFast(id);
    }

    @PutMapping("/{id}")
    public void updateById (@PathVariable Long id, @RequestBody UserJPA user) {
        userService.update(id,user);
    }

    @DeleteMapping("/{id}")
    public void deleteById (@PathVariable Long id) {
        userService.deleteById(id);
    }
}
