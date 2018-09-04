package com.kevin.demo.repository.dao;

import com.kevin.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Kevin on 2018/8/12 0012.
 * Desc:
 */
public interface UserDao extends PagingAndSortingRepository<User, String>, JpaSpecificationExecutor<User>, JpaRepository<User, String>, UserDaoCustom {

    User findByUserName(String name);
}
