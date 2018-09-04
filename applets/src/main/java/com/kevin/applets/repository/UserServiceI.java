package com.kevin.applets.repository;

import com.kevin.applets.entity.DataGrid;
import com.kevin.applets.entity.User;

/**
 * Created by Kevin on 2018/8/12 0012.
 * Desc:
 */
public interface UserServiceI {
    User findUserByName(String name);

    User saveUser(User user);

    DataGrid<User> list(User user);
}
