package com.task.services;

import com.task.model.Task;
import com.task.model.TaskStatus;

import java.util.List;

public interface TaskServices {
    Task createTask(Task task, String requestRole) throws Exception;
    Task getTaskById(Long id) throws Exception;
    List<Task> getAllTask(TaskStatus status);
    Task updateTask(Long id, Task updatedTask, Long userId) throws Exception;
    void deleteTask(Long id) throws Exception;
    Task assignedToUser(Long userId, Long taskId) throws Exception;
    List<Task> assignedUserTask(Long userId, TaskStatus status);
    Task completeTask(Long taskId) throws Exception;

}
