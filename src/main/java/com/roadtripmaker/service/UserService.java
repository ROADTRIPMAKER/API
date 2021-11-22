package com.roadtripmaker.service;

import com.roadtripmaker.domain.model.Role;
import com.roadtripmaker.domain.model.User;

import java.util.List;

public interface UserService {
    User signUp(User user);

    User getUser(String mail);

    List<User> getUsers();

    Role addRole(Role role);

    void assignRoleToAnUser(String mail, String nameRole²);
}
