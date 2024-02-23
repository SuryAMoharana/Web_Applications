package com.task.controller;

import com.task.model.Task;
import com.task.model.TaskStatus;
import com.task.model.UserDto;
import com.task.services.TaskServices;
import com.task.services.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    @Autowired
    private TaskServices taskServices;

    @Autowired
    private UserService userService;

    @PostMapping("/createTask")
    public ResponseEntity<Task> createTask(@RequestBody Task task, @RequestHeader("Authorization") String jwt) throws Exception{
        UserDto user=userService.getUserProfile(jwt);
        Task createdTask=taskServices.createTask(task,user.getRole());
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id,
                                            @RequestHeader("Authorization")String jwt) throws Exception{
        UserDto user=userService.getUserProfile(jwt);
        Task task=taskServices.getTaskById(id);
        return new ResponseEntity<>(task, HttpStatus.OK);

    }

    @GetMapping("/user")
    public ResponseEntity<List<Task>> getAssignedUsersTask(@RequestParam(required = false)TaskStatus status,
                                                           @RequestHeader("Authorization")String jwt) throws Exception{
        UserDto user=userService.getUserProfile(jwt);
        List<Task> tasks=taskServices.assignedUserTask(user.getId(), status);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/alltasks")
    public ResponseEntity<List<Task>> getAllTasks(@RequestParam(required = false)TaskStatus status,
                                                  @RequestHeader("Authorization")String jwt) throws Exception{
        UserDto user=userService.getUserProfile(jwt);
        List<Task> tasks=taskServices.getAllTask(status);
        return new ResponseEntity<>(tasks,HttpStatus.OK);
    }

    @PutMapping("/{id}/user/{userId}/assigned")
    public ResponseEntity<Task> assignedTaskToUser(@PathVariable Long id,
                                                         @PathVariable Long userId,
                                                         @RequestHeader("Authorization")String jwt) throws Exception{
        UserDto user=userService.getUserProfile(jwt);
        Task tasks=taskServices.assignedToUser(userId,id);
        return new ResponseEntity<>(tasks, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Task> updatetask(@PathVariable Long id,
                                           @RequestBody Task req,
                                           @RequestHeader("Authorization")String jwt) throws Exception{
        UserDto user=userService.getUserProfile(jwt);
        Task task=taskServices.updateTask(id,req,user.getId());
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> completeTask(@PathVariable Long id) throws Exception{
        Task tasks=taskServices.completeTask(id);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) throws Exception{
        taskServices.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
