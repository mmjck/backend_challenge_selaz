package com.selaz.ms.core.domain.user.gateway;

import java.util.List;

import com.selaz.ms.core.domain.task.entity.Task;
import com.selaz.ms.core.domain.user.entity.User;

public interface UserGateway {
    public User save(User user);
    public User update(Long id, User data);
    public List<User> findAll(); 
    public void delete(Long id);


    public User findByUsername(String username );
    public List<Task> findTasksByUserId(Long id);


}
