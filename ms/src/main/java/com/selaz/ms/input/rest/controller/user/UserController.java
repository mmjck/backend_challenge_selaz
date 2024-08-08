package com.selaz.ms.input.rest.controller.user;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.selaz.ms.input.rest.controller.task.dto.TaskResponseDTO;
import com.selaz.ms.input.rest.controller.user.dto.CreateUserRequestDTO;
import com.selaz.ms.input.rest.controller.user.dto.ListTaskResponseDTO;
import com.selaz.ms.input.rest.controller.user.dto.ListUserResponseDTO;
import com.selaz.ms.input.rest.controller.user.dto.UpdateUserRequestDTO;
import com.selaz.ms.input.rest.controller.user.dto.UserResponseDTO;
import com.selaz.ms.service.user.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/users/")
public class UserController implements UserApi {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    @Override
    public ResponseEntity<UserResponseDTO> create(@RequestBody CreateUserRequestDTO request) {
        var response = this.service.create(request.username(), request.nivel());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new UserResponseDTO(response.getId(), response.getUsername(), response.getNivel()));
    }

    @PutMapping("{id}")
    @Override
    public ResponseEntity<UserResponseDTO> update(
            @PathVariable("id") Long id,
            @RequestBody UpdateUserRequestDTO request) {
        var response = this.service.update(id, request.username(), request.nivel());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new UserResponseDTO(response.getId(), response.getUsername(), response.getNivel()));
    }

    @GetMapping
    @Override
    public ResponseEntity<ListUserResponseDTO> listAll() {
        var users = this.service.getAll();

        var usersDtoMapped = users.stream().map(u -> new UserResponseDTO(u.getId(), u.getUsername(), u.getNivel()))
                .toList();

        var response = new ListUserResponseDTO(users.size(), usersDtoMapped);

        return ResponseEntity.ok().body(response);

    }

    @Override
    @GetMapping("{id}/tasks/")
    public ResponseEntity<ListTaskResponseDTO> listAll(
        @PathVariable("id") Long id) {
        var tasks = this.service.getAllTaskByUserId(id);

        var taskDtoMapped = tasks.stream()
                .map(u -> new TaskResponseDTO(u.getId(), u.getTitle(), u.getDescription(), u.getStatus(),
                        u.getDueDate(), u.getCreatedAt()))
                .toList();

        var response = new ListTaskResponseDTO(tasks.size(), taskDtoMapped);

        return ResponseEntity.ok().body(response);
    }
    
    @Override
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        this.service.delete(id);
        return ResponseEntity.ok().build();
    }

}
