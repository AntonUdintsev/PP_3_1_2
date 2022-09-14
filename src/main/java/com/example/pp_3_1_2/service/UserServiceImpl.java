package com.example.pp_3_1_2.service;


import com.example.pp_3_1_2.dao.UserDao;
import com.example.pp_3_1_2.model.User;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;


    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> getUsers() {
        return userDao.getUsers();
    }

    @Override
    public User findByName (String name) {
        return userDao.findByName(name);
    }
    @Override
    @Transactional
    public void addUser (User user){
        userDao.addUser(user);
    }

    @Override
    public User showUser(int id) {
        return userDao.showUser(id);
    }

    @Override
    @Transactional
    public void updateUser(int id, User user) {
        userDao.updateUser(id,user);
    }

    @Override
    @Transactional
    public void deleteUser(int id) {
        userDao.deleteUser(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDao.findByName(username);
    }
}
