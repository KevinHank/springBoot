package com.kevin.demo.controller;

import com.kevin.demo.entity.DataGrid;
import com.kevin.demo.entity.User;
import com.kevin.demo.json.JsonFilter;
import com.kevin.demo.repository.UserServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;

/**
 * Created by Kevin on 2018/8/12 0012.
 * Desc:
 */

@RestController
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserServiceI userService;

    @RequestMapping("/list")
    @JsonFilter(type = User.class, exclude = "password")
    public DataGrid<User> list(User user) {

        return userService.list(user);
    }

    @RequestMapping("/getUserByName")
    public User getUserById(String name) {

        return userService.findUserByName(name);
    }

    @RequestMapping("/save")
    public User saveUser(User user) throws Exception {
        user.setUserName("kevin");
        user.setAddress("湖北武汉");
        user.setBirthDay(new SimpleDateFormat("yyyy-MM-dd").parse("1992-09-24"));
        user.setSex("男");

        return userService.saveUser(user);
    }
}
