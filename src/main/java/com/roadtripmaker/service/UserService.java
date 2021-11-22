package com.roadtripmaker.service;

import com.roadtripmaker.domain.model.RoadUser;
import com.roadtripmaker.domain.model.Role;

import java.util.List;

public interface UserService {
    RoadUser signUp(RoadUser roadUser);

    RoadUser getUser(String mail);

    List<RoadUser> getUsers();

    Role addRole(Role role);

    void assignRoleToAnUser(String mail, String nameRole);
}
