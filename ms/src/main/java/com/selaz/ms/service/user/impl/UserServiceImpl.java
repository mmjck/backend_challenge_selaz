package com.selaz.ms.service.user.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.selaz.ms.core.domain.task.entity.Task;
import com.selaz.ms.core.domain.user.entity.Nivel;
import com.selaz.ms.core.domain.user.entity.User;
import com.selaz.ms.core.domain.user.gateway.UserGateway;
import com.selaz.ms.service.user.UserService;

@Service
public class UserServiceImpl implements UserService{

    private UserGateway gateway;

    public UserServiceImpl(UserGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public User create(String username, Nivel nivel) {
        var user = User.builder().username(username).nivel(nivel).build();
        return this.gateway.save(user);
    }

    @Override
    public void delete(Long id) {
        this.gateway.delete(id);
    }

    @Override
    public List<User> getAll() {
        return this.gateway.findAll();
    }

    @Override
    public User update(Long id, String username, Nivel nivel) {
        var user = User.builder().username(username).nivel(nivel).build();
        return this.gateway.update(id, user);
    }

    @Override
    public List<Task> getAllTaskByUserId(Long id) {
        return this.gateway.findTasksByUserId(id);
    }
    
    @Override
    public User findByUsername(String username) {
        return this.gateway.findByUsername(username);
        
    }
}
