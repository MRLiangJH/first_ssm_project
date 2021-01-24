package com.atSCUT.service;

import com.atSCUT.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserManageTest extends BaseTest {

    @Autowired
    private UserManage userManage;

    @Test
    public void testIsUserExist() {
        boolean userExist = userManage.isUserExist("wangwu");
        System.out.println(userExist);
    }
}
