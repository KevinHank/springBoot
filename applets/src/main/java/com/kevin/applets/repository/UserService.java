package com.kevin.applets.repository;

import com.kevin.applets.entity.DataGrid;
import com.kevin.applets.entity.User;
import com.kevin.applets.repository.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Kevin on 2018/8/12 0012.
 * Desc:
 */
@Service
public class UserService implements UserServiceI {
    @Autowired
    private UserDao userDao;

    @Override
    public User findUserByName(String name) {

        return userDao.findByUserName(name);
    }

    @Override
    public User saveUser(User user) {
        return userDao.save(user);
    }

    @Override
    public DataGrid<User> list(User user) {

        return userDao.list(user);
    }
}
