package com.kevin.applets.repository.dao;

import com.kevin.applets.entity.DataGrid;
import com.kevin.applets.entity.User;

/**
 * Created by Hukai
 * 2018-08-13 0013 上午 10:25
 */
public interface UserDaoCustom {

    DataGrid<User> list(User user);
}
