package com.atSCUT.service;

import com.atSCUT.domain.User;

public interface UserManage {

    boolean isUserExist(String name);

    boolean registerUser(User user);

    void unregisterUser(User user);


}
