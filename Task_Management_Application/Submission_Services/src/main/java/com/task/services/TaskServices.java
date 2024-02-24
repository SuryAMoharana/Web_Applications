package com.task.services;


import com.task.model.TaskDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "TaskServices", url="http://localhost:8181")
public interface TaskServices {
    @GetMapping("/api/tasks/{id}")
    public TaskDto getTaskById(@PathVariable Long id,
                                               @RequestHeader("Authorization")String jwt) throws Exception;
    @PutMapping("/api/tasks/{id}")
    public TaskDto completeTask(@PathVariable Long id) throws Exception;


}
