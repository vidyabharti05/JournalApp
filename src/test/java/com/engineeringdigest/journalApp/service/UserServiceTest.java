package com.engineeringdigest.journalApp.service;

import com.engineeringdigest.journalApp.repository.UserEntryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.engineeringdigest.journalApp.entity.User;

import static org.junit.jupiter.api.Assertions.*;

@Disabled
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserEntryRepository userEntryRepository;
    @Autowired
    private UserService userService;


    @ParameterizedTest
    @ArgumentsSource(UserArgumentsprovider.class)
    public void testSaveNewUser(User user) {
     assertTrue(userService.saveNewUser(user));
    }

    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "2,10,12",
            "3,3,9"

    })
    public void test(int a,int b,int expected){
        assertEquals(expected, a + b);
    }
}
