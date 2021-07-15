package com.knowit.lectures.services.users;

import com.knowit.lectures.domain.entities.User;
import com.knowit.lectures.exceptions.UserDoesNotExist;

public interface UserService {

    User fetchUserById(String userId) throws UserDoesNotExist;
}
