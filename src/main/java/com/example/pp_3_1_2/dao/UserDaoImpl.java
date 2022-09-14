package com.example.pp_3_1_2.dao;



import com.example.pp_3_1_2.model.Role;
import com.example.pp_3_1_2.model.User;


import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;


@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;
    private PasswordEncoder bCryptPasswordEncoder;

    public UserDaoImpl(@Lazy PasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public List<User> getUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }


    @Override
    public void addUser(User user) {
            user.setRoles(Collections.singleton(new Role(2L, "ROLE_USER")));
            user.setUserPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            entityManager.persist(user);
    }

    @Override
    public User showUser(int id) {
        return entityManager.find(User.class,id);
    }

    @Override
    public void updateUser(int id, User user) {
        user.setRoles(Collections.singleton(new Role(2L, "ROLE_USER")));
        entityManager.merge(user);
    }

    @Override
    public void deleteUser(int id) {
        entityManager.remove(showUser(id));
    }

    @Override
    public User findByName (String name) {
        User user = entityManager.createQuery("select u from User u WHERE u.userName = :ParamName", User.class).setParameter("ParamName",name).getSingleResult();
        return user;
    }


}
