package com.engineeringdigest.journalApp.service;

import com.engineeringdigest.journalApp.entity.User;
import com.engineeringdigest.journalApp.repository.UserEntryRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class UserService {
    @Autowired
    private UserEntryRepository userEntryRepository;

    public static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

   // private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public void saveEntry(User user){
        userEntryRepository.save(user);
    }

    public boolean saveNewUser(User user){
        try{
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(List.of("USER"));
            userEntryRepository.save(user);
            return true;
        } catch (Exception e) {
            //logger.error("error acured for {}:",user.getUsername(),e);
            log.error("error acured for {}:",user.getUsername(),e);


            return false;
        }

    }
    public void saveAdmin(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER","ADMIN"));
        userEntryRepository.save(user);
    }

    public List<User> getAll(){
        return userEntryRepository.findAll();
    }

    public Optional<User> findById(ObjectId id){
        return userEntryRepository.findById(id);
    }

    public void deleteById(ObjectId id){
        userEntryRepository.deleteById(id);
    }

    public User findByUserName(String userName) {
        return (User) userEntryRepository.findByUsername(userName);
    }
}


