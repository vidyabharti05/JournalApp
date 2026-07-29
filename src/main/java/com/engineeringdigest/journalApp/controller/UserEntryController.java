package com.engineeringdigest.journalApp.controller;

import com.engineeringdigest.journalApp.config.SecurityConfig;
import com.engineeringdigest.journalApp.repository.UserEntryRepository;
import com.engineeringdigest.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import com.engineeringdigest.journalApp.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/user")
public class UserEntryController {
    @Autowired
    private UserService userEntryService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserEntryRepository userEntryRepository;

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User userInDb = userEntryService.findByUserName(userName);
         if(userInDb != null) {
             userInDb.setUsername(user.getUsername());
             userInDb.setPassword(passwordEncoder.encode(user.getPassword()));
             userEntryService.saveEntry(userInDb);
         }
         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping
    public ResponseEntity<?> deleteUser(@RequestBody User user){
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        userEntryRepository.deleteByUsername(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}