package com.selaz.ms.repository.user;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.selaz.ms.core.domain.task.entity.Task;
import com.selaz.ms.core.domain.user.entity.User;
import com.selaz.ms.core.domain.user.gateway.UserGateway;
import com.selaz.ms.core.exceptions.user.UserAlreadyExistsException;
import com.selaz.ms.core.exceptions.user.UserNotFoundException;
import com.selaz.ms.repository.task.jpa.TaskJpaRepository;
import com.selaz.ms.repository.task.jpa.mapper.TaskJpaModel2TaskMapper;
import com.selaz.ms.repository.user.jpa.UserJpaRepository;
import com.selaz.ms.repository.user.jpa.mapper.User2UserJpaModelMapper;
import com.selaz.ms.repository.user.jpa.mapper.UserJpaModel2UserMapper;

@Repository
public class UserJpaGateway implements UserGateway {
    private UserJpaRepository repository;
    private TaskJpaRepository taskJpaRepository;

    public UserJpaGateway(UserJpaRepository repository, TaskJpaRepository taskJpaRepository) {
        this.repository = repository;
        this.taskJpaRepository = taskJpaRepository;
    }

    @Override
    public User save(User user) {
        var finded = this.repository.findByUsername(user.getUsername());

        if(finded.isPresent()){
            throw new UserAlreadyExistsException();
        }


        System.out.println("WASS CALLED");
        var entity = User2UserJpaModelMapper.mapper(user);

        var response = this.repository.save(entity);
        return UserJpaModel2UserMapper.mapper(response);
    }

    @Override
    public User update(Long id, User data) {

        var user = this.repository.findById(id).orElseThrow(() -> new UserNotFoundException());

        if (!data.getUsername().isEmpty()) {
            if (!user.getUsername().equals(data.getUsername())) {
                user.setUsername(data.getUsername());
            }
        }

        if (data.getNivel() != null) {
            if (!user.getNivel().equals(data.getNivel())) {
                user.setNivel(data.getNivel());
            }
        }

        var response = this.repository.saveAndFlush(user);
        return UserJpaModel2UserMapper.mapper(response);
    }

    @Override
    public List<User> findAll() {
        var response = this.repository.findAll();
        return response.stream().map(UserJpaModel2UserMapper::mapper).toList();
    }

    @Override
    public void delete(Long id) {
        this.repository.deleteById(id);
    }

    @Override
    public List<Task> findTasksByUserId(Long id) {
        var tasks = this.taskJpaRepository.findByUserId(id);
        return tasks.stream().map(TaskJpaModel2TaskMapper::mapper).toList();
    }

    @Override
    public User findByUsername(String username) {
        var user = this.repository.findByUsername(username);

        if (!user.isPresent()) {
            return null;
        }

        return UserJpaModel2UserMapper.mapper(user.get());
    }

}
