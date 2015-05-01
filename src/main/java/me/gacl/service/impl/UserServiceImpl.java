package me.gacl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import me.gacl.dao.UserMapper;
import me.gacl.domain.User;
import me.gacl.service.UserServiceI;

/**
 * @author gacl
 * 使用@Service注解将UserServiceImpl类标注为一个service
 * service的id是userService
 */
@Service("userService")
public class UserServiceImpl implements UserServiceI {

    /**
     * 使用@Autowired注解标注userMapper变量，
     * 当需要使用UserMapper时，Spring就会自动注入UserMapper
     */
    @Autowired
    private UserMapper userMapper;//注入dao

    public void addUser(User user) {
        userMapper.insert(user);
    }

    public User getUserById(String userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    public List<User> getAllUser() {
        return userMapper.getAllUser();
    }
}