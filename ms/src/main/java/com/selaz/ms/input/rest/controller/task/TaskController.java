package com.selaz.ms.input.rest.controller.task;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.PrincipalMethodArgumentResolver;

import com.selaz.ms.core.domain.task.entity.Status;
import com.selaz.ms.core.domain.user.entity.User;
import com.selaz.ms.input.rest.controller.task.dto.CreateTaskRequestDTO;
import com.selaz.ms.input.rest.controller.task.dto.TaskResponseDTO;
import com.selaz.ms.input.rest.controller.task.dto.UpdateTaskRequestDTO;
import com.selaz.ms.input.rest.controller.user.dto.ListTaskResponseDTO;
import com.selaz.ms.service.task.TaskService;

import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.security.core.Authentication;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/task/")
public class TaskController implements TaskApi {

        private TaskService service;

        public TaskController(TaskService service) {
                this.service = service;
        }

        @PostMapping
        @Override
        public ResponseEntity<TaskResponseDTO> create(
                        @RequestBody CreateTaskRequestDTO request,
                        @Parameter(hidden = true) PrincipalMethodArgumentResolver principal) {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                User user = (User) authentication.getPrincipal();
                var response = this.service.create(request.title(), request.description(), request.dueDate(),
                                user.getId());
                return ResponseEntity.status(HttpStatus.CREATED)
                                .body(new TaskResponseDTO(response.getId(), response.getTitle(),
                                                response.getDescription(),
                                                response.getStatus(), response.getDueDate(),
                                                response.getCreatedAt()));
        }

        @Override
        @PutMapping("{id}")
        public ResponseEntity<TaskResponseDTO> update(
                        @PathVariable("id") Long id,
                        @RequestBody UpdateTaskRequestDTO request) {
                var response = this.service.update(id, request.title(), request.description(), request.status(),
                                request.dueDate());
                return ResponseEntity.status(HttpStatus.CREATED)
                                .body(new TaskResponseDTO(response.getId(), response.getTitle(),
                                                response.getDescription(),
                                                response.getStatus(), response.getDueDate(),
                                                response.getCreatedAt()));
        }

        @DeleteMapping("{id}")
        @Override
        public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
                this.service.delete(id);
                return ResponseEntity.ok().build();
        }

        @GetMapping("{userId}")
        @Override
        public ResponseEntity<ListTaskResponseDTO> listAll(
                        @PathVariable("userId") Long userId,
                        @RequestParam(name = "status", required = false) Status status,
                        @RequestParam(name = "sort", required = false) String sort) {

                var tasks = this.service.getAllTaskByUserId(userId, status, sort != null && sort.equals("dueDate"));
                var taskDtoMapped = tasks.stream()
                                .map(u -> new TaskResponseDTO(u.getId(), u.getTitle(), u.getDescription(),
                                                u.getStatus(),
                                                u.getDueDate(), u.getCreatedAt()))
                                .toList();

                var response = new ListTaskResponseDTO(taskDtoMapped.size(), taskDtoMapped);

                return ResponseEntity.ok().body(response);
        }

}
