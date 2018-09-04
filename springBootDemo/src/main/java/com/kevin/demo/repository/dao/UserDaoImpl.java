package com.kevin.demo.repository.dao;

import com.kevin.demo.entity.DataGrid;
import com.kevin.demo.entity.User;
import com.kevin.demo.util.PagingUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Hukai
 * 2018-08-13 0013 上午 10:25
 */
@Service
public class UserDaoImpl implements UserDaoCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public DataGrid<User> list(User user) {
        DataGrid<User> grid = new DataGrid<>();

        StringBuilder hql = new StringBuilder("from User u where 1 = 1 ");
        Map<String, Object> paramMap = new HashMap<>();

        if (StringUtils.isNotBlank(user.getUserName())) {
            hql.append(" and u.userName like :userName");
            paramMap.put("userName", "%" + user.getUserName() + "%");
        }

        if (StringUtils.isNotBlank(user.getSex())) {
            hql.append(" and u.sex = :sex");
            paramMap.put("sex", user.getSex());
        }

        return PagingUtils.exeHql(entityManager, grid, hql.toString(), paramMap, user.getPagingDto(), "u");
    }
}
