package com.ratiose.testtask.service.impl;

import com.ratiose.testtask.entity.User;
import com.ratiose.testtask.exception.RequestException;
import com.ratiose.testtask.repository.UserRepository;
import com.ratiose.testtask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException;

import static java.util.Objects.nonNull;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public User registerUser(String email, String password) {
        checkInputEmailAndPassword(email, password);
        User user = userRepository.findByEmail(email);
        if (user != null) {
            throw new RequestException(HttpStatus.BAD_REQUEST,
                  String.format("User with specified email: %s and password: %s already exists", email, password));
        }
        user = createUser(email, password);
        return userRepository.save(user);
    }

    @Override
    public User findUser(String email, String password) {
        User foundUser = userRepository.findByEmail(email);
        if (nonNull(foundUser)) {
            if (passwordEncoder.matches(password, foundUser.getPassword())) {
                return foundUser;
            }
        }
        throw new RequestException(HttpStatus.NOT_FOUND,
            String.format("User with specified email: %s and password: %s does not exist", email, password));
    }

    private User createUser(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        return user;
    }

    private void checkInputEmailAndPassword(String email, String password) {
        if (StringUtils.isEmpty(email) || StringUtils.isEmpty(password)) {
            throw new RequestException(HttpStatus.UNAUTHORIZED, "Email and password should not be empty");
        }
    }
}
