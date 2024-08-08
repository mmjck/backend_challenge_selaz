package com.selaz.ms.service.user;

import java.util.List;

import com.selaz.ms.core.domain.task.entity.Task;
import com.selaz.ms.core.domain.user.entity.Nivel;
import com.selaz.ms.core.domain.user.entity.User;

public interface UserService {
        public User create(String username, Nivel nivel);

        public User update(Long id, String username, Nivel nivel);
        public void delete(Long id);


        public User findByUsername(String username);

        public List<User> getAll();


        public List<Task> getAllTaskByUserId(Long id);

}
