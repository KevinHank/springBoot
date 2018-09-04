package com.kevin.demo.repository.dao;

import com.kevin.demo.entity.DataGrid;
import com.kevin.demo.entity.User;

/**
 * Created by Hukai
 * 2018-08-13 0013 上午 10:25
 */
public interface UserDaoCustom {

    DataGrid<User> list(User user);
}
