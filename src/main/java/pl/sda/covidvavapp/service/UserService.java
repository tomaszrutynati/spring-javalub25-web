package pl.sda.covidvavapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.covidvavapp.repository.UserEntity;
import pl.sda.covidvavapp.repository.UserRepository;
import pl.sda.covidvavapp.web.model.NewUser;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void registerUser(NewUser newUser) {
        UserEntity entity = UserEntity.builder()
                .password(newUser.getPassword())
                .username(newUser.getUsername())
                .role(newUser.getRole())
                .build();
        userRepository.save(entity);
    }
}
