package com.selaz.ms.repository.task;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.selaz.ms.core.domain.task.entity.Status;
import com.selaz.ms.core.domain.task.entity.Task;
import com.selaz.ms.core.domain.task.gateway.TaskGateway;
import com.selaz.ms.core.exceptions.task.TaskNotFoundException;
import com.selaz.ms.core.exceptions.user.UserNotFoundException;
import com.selaz.ms.repository.task.jpa.TaskJpaRepository;
import com.selaz.ms.repository.task.jpa.mapper.Task2TaskJpaModelMapper;
import com.selaz.ms.repository.task.jpa.mapper.TaskJpaModel2TaskMapper;
import com.selaz.ms.repository.task.jpa.model.TaskJpaModel;
import com.selaz.ms.repository.user.jpa.UserJpaRepository;

@Repository
public class TaskJpaGateway implements TaskGateway {
    private TaskJpaRepository repository;
    private UserJpaRepository userJpaRepository;

    public TaskJpaGateway(TaskJpaRepository repository, UserJpaRepository userJpaRepository) {
        this.repository = repository;
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public Task save(Task data) {
        TaskJpaModel entity = Task2TaskJpaModelMapper.mapper(data);

        var user = this.userJpaRepository.findById(data.getUserId())
                .orElseThrow(() -> new UserNotFoundException());

                entity.setUser(user);
        var response = this.repository.save(entity);
        return TaskJpaModel2TaskMapper.mapper(response);

    }

    @Override
    public void delete(Long id) {
        this.repository.deleteById(id);
    }

    @Override
    public Task update(Long id, Task data) {
        var task = this.repository.findById(id).orElseThrow(() -> new TaskNotFoundException());

        if (!data.getDescription().isEmpty()) {
            if (!data.getDescription().equals(data.getDescription())) {
                task.setDescription(data.getDescription());
            }
        }

        if (data.getDueDate() != null) {
            if (!task.getDueDate().isEqual(data.getDueDate())) {
                task.setDueDate(data.getDueDate());
            }
        }

        if (data.getTitle() != null) {
            if (!task.getTitle().equals(data.getTitle())) {
                task.setTitle(data.getTitle());
            }
        }

        if (data.getStatus() != null) {
            if (!task.getStatus().equals(data.getStatus())) {
                task.setStatus(data.getStatus());
            }
        }

        var response = this.repository.saveAndFlush(task);
        return TaskJpaModel2TaskMapper.mapper(response);
    }

    @Override
    public List<Task> findByUserId(Long id, Status status, Boolean orderByDueDate) {
        List<TaskJpaModel> response = this.repository.findAllWithFilter(id, status, orderByDueDate);

        return response
                .stream()
                .map(TaskJpaModel2TaskMapper::mapper)
                .toList();

    }

}
