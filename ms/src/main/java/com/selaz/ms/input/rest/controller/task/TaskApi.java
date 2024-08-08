package com.selaz.ms.input.rest.controller.task;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.PrincipalMethodArgumentResolver;

import com.selaz.ms.core.domain.task.entity.Status;
import com.selaz.ms.input.rest.controller.task.dto.CreateTaskRequestDTO;
import com.selaz.ms.input.rest.controller.task.dto.TaskResponseDTO;
import com.selaz.ms.input.rest.controller.task.dto.UpdateTaskRequestDTO;
import com.selaz.ms.input.rest.controller.user.dto.ListTaskResponseDTO;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;



import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Task", description = "task endpoints")
public interface TaskApi {
    
    @Operation(summary = "Create task")
    @ApiResponse(responseCode = "201", description = "Task created", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TaskResponseDTO.class)))
    public ResponseEntity<TaskResponseDTO> create(
                        @RequestBody CreateTaskRequestDTO request,
                        PrincipalMethodArgumentResolver principal);

    @Operation(summary = "Update task by id")
    @ApiResponse(responseCode = "200", description = "Task updated", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TaskResponseDTO.class)))
    public ResponseEntity<TaskResponseDTO> update(
                        @PathVariable("id") Long id,
                        @RequestBody UpdateTaskRequestDTO request);

    @Operation(summary = "Delete task by id")
    @ApiResponse(responseCode = "200", description = "Task deleted")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id);

    @ApiResponse(responseCode = "200", description = "All tasks with filter applied listed", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ListTaskResponseDTO.class)))
    @Operation(summary = "Get all tasks and filter it")
    public ResponseEntity<ListTaskResponseDTO> listAll(
                        @PathVariable("id") Long id,
                        @RequestParam(name = "status", required = false) Status status,
                        @RequestParam(name = "sort", required = false) String sort);

}
