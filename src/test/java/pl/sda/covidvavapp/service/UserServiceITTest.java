package pl.sda.covidvavapp.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.sda.covidvavapp.repository.UserEntity;
import pl.sda.covidvavapp.repository.UserRepository;
import pl.sda.covidvavapp.web.model.NewUser;

import java.util.List;

@SpringBootTest
public class UserServiceITTest {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void beforeTest() {
        userRepository.deleteAll();
    }

    @Test
    public void shouldRegisterNewUser() {
        //given
        NewUser newUser = new NewUser("test", "pass", "USER");
        //when
        userService.registerUser(newUser);
        //then
        List<UserEntity> allUsers = userRepository.findAll();
        Assertions.assertEquals(1, allUsers.size());
        UserEntity registredUser = allUsers.get(0);
        Assertions.assertEquals("USER", registredUser.getRole());
        Assertions.assertEquals("test", registredUser.getUsername());
        Assertions.assertTrue(passwordEncoder.matches("pass", registredUser.getPassword()));
    }
}
