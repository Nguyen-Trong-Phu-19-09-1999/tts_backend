package com.mycompany.controller;

import com.mycompany.dto.UserDto;
import com.mycompany.model.Users;
import com.mycompany.service.itf.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/user")

public class UserController {
    @Autowired
    private IUserService userService ;

    @GetMapping
    public List findAll() {
        return userService.findAll();
    }

    @PostMapping ("/login")
    public ResponseEntity<String> login(@RequestBody Users users)
    {
        Users user = userService.findUsersByUsername(users.getUsername());

        if (user != null) {
            if (user.getPassword().equals(users.getPassword())) { // Ideally, use a secure password comparison
                return new ResponseEntity<>("Login successful", HttpStatus.OK);
            }
        }
            return new ResponseEntity<>("Login fails", HttpStatus.NOT_FOUND);
    }
    @PostMapping ("/register")
    public ResponseEntity<?> register(@RequestBody UserDto users) {
        return userService.save(users);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return userService.delete(id);
    }

}
