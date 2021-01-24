package com.atSCUT.service.impl;

import com.atSCUT.dao.UserDao;
import com.atSCUT.domain.User;
import com.atSCUT.service.UserManage;
import org.springframework.stereotype.Service;

@Service
public class UserManageImpl implements UserManage {

    private final UserDao userDao;

    public UserManageImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public boolean isUserExist(String username) {
        User user = userDao.findByName(username);
        if (user == null) {
            return false;
        }
        return true;
    }

    @Override
    public boolean registerUser(User user) {
        int i = userDao.addUser(user);
        return i == 1;
    }

    @Override
    public void unregisterUser(User user) {

    }
}
