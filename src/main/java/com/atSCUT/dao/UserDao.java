package com.atSCUT.dao;

import com.atSCUT.domain.User;

public interface UserDao {

    int addUser(User user);

    void deleteUser(User user);

    User findByName(String name);
}
