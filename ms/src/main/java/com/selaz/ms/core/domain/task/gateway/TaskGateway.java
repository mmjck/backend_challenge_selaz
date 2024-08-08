package com.selaz.ms.core.domain.task.gateway;

import java.util.List;

import com.selaz.ms.core.domain.task.entity.Status;
import com.selaz.ms.core.domain.task.entity.Task;




public interface TaskGateway {
    public Task save(Task data);
    public Task update(Long id, Task data);

    public void delete(Long id);
    public List<Task> findByUserId(Long id, Status status, Boolean orderByDueDate);

}
