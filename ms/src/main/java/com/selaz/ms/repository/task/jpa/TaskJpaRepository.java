package com.selaz.ms.repository.task.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.selaz.ms.core.domain.task.entity.Status;
import com.selaz.ms.repository.task.jpa.model.TaskJpaModel;
import java.util.List;

public interface TaskJpaRepository extends JpaRepository<TaskJpaModel, Long> {
    @Query("SELECT t FROM Task t " +
            "WHERE (:userId IS NULL OR t.user.id = :userId) " +
            "AND (:status IS NULL OR t.status = :status) " +
            "AND :orderByDueDate IS TRUE ORDER BY t.dueDate")
    List<TaskJpaModel> findAllWithFilter(
            @Param("userId") Long userId,
            @Param("status") Status status,
            @Param("orderByDueDate") Boolean orderByDueDate
    );

    List<TaskJpaModel> findByUserId(Long userId);
}