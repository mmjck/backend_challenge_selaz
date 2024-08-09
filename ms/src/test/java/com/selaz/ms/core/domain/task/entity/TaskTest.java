package com.selaz.ms.core.domain.task.entity;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.Long;

public class TaskTest {
    @Test
    void testGetTitle() {
        Task task = new Task(Long.valueOf(1), Status.CONCLUIDA, Long.valueOf(1), "title", "description",
                LocalDateTime.now(), LocalDateTime.now());

        assertEquals("title", task.getTitle());
    }

    @Test
    void testGetDescription() {
        Task task = new Task(Long.valueOf(1), Status.CONCLUIDA, Long.valueOf(1), "title", "description",
                LocalDateTime.now(), LocalDateTime.now());

        assertEquals("description", task.getDescription());
    }

    @Test
    void testGetDueDate() {
        var date =  LocalDateTime.of(2020, 12, 01, 0, 0, 0);
        Task task = new Task(Long.valueOf(1), Status.CONCLUIDA, Long.valueOf(1), "title", "description",
               date, date);

        assertEquals(date.isEqual(task.getDueDate()), true);
        assertEquals(date.isEqual(task.getCreatedAt()), true);
    }
}
