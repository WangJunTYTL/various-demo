package com.peaceful.demo.spring.service;

import com.peaceful.demo.spring.dao.UserDao;
import com.peaceful.demo.spring.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.List;

/**
 * Created by wangjun on 14/12/25.
 */
@Component
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PlatformTransactionManager txManager;


    public User getUserById(long id) {
        return userDao.getUser(id);
    }

    // 编程式事务
    public void insert(User user) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        // 设置事务的名字
        def.setName("UserService.insertUser");
        // 设置事务的传播属性
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        // 事务状态
        def.setTimeout(100);
        TransactionStatus status = txManager.getTransaction(def);
        try {
            userDao.insertUser(user);
            queryUserById(1);
            txManager.commit(status);
        } catch (Exception ex) {
            txManager.rollback(status);
            throw new RuntimeException(ex);
        }
    }

    // 编程式事务
    @Transactional(propagation = Propagation.REQUIRED)
    public void insert2(User user) {
        userDao.insertUser(user);
        // 注意，如果使用主注解，这时在这个方法配置注解并不会生效，在同一类中，g该方法不会被代理
//        http://stackoverflow.com/questions/18590170/transactional-does-not-work-on-method-level
        queryUserById(1);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public User queryUserById(long id) {
//        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
//        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_NEVER);
//        TransactionStatus status = txManager.getTransaction(def);
        return userDao.getUser(id);
    }

    public List<User> queryByName(String nameLike) {
        return userDao.queryUsersByName(nameLike);
    }


}
