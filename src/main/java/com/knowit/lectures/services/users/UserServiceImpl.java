package com.knowit.lectures.services.users;

import com.knowit.lectures.domain.entities.User;
import com.knowit.lectures.exceptions.UserDoesNotExist;
import com.knowit.lectures.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User fetchUserById(String userId) throws UserDoesNotExist {
        return this.userRepository.findById(userId).orElseThrow(() -> new UserDoesNotExist());
    }
}
