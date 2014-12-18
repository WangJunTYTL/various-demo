package com.peaceful.demo.rose.dao;

import com.peaceful.demo.rose.domain.User;
import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.annotation.SQLParam;
import org.springframework.stereotype.Component;

/**
 * Created by wangjun on 14/12/9.
 */
@DAO
@Component
public interface UserDAO {

    @SQL("select * from user limit 1")
    public User getUser();

    @SQL("insert into user (name) values (:u.name)")
    public void insertUser(@SQLParam("u") User user);


}
