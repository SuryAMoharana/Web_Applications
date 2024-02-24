package com.task.controller;

import com.task.model.Submission;
import com.task.model.UserDto;
import com.task.services.SubmissionServices;
import com.task.services.TaskServices;
import com.task.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/submissions")
public class SubmissionControllers {
    @Autowired
    private SubmissionServices submissionServices;

    @Autowired
    private TaskServices taskServices;

    @Autowired
    private UserServices userServices;


    @PostMapping("/submit")
    public ResponseEntity<Submission> submitTask(
            @RequestParam Long taskId,
            @RequestParam String githubLink,
            @RequestHeader("Authorization") String jwt
    ) throws Exception{
        UserDto user=userServices.getUserProfile(jwt);
        Submission submission=submissionServices.submitTask(taskId,githubLink,user.getId(),jwt);
        return new ResponseEntity<>(submission, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Submission> getSubmissionById(
            @PathVariable Long id,
            @RequestHeader("Authorization") String jwt
    )throws Exception{
        UserDto user=userServices.getUserProfile(jwt);
        Submission submission=submissionServices.getTaskSubmissionById(id);
        return new ResponseEntity<>(submission, HttpStatus.OK);
    }

    @GetMapping("/allSubmission")
    public ResponseEntity<List<Submission>> getAllSubmissions(
            @RequestHeader("Authorization") String jwt
    ) throws Exception{
        UserDto user=userServices.getUserProfile(jwt);
        List<Submission> submissions=submissionServices.getAllTaskSubmission();
        return new ResponseEntity<>(submissions,HttpStatus.OK);
    }

    @GetMapping("/task/{taskId}")
    public ResponseEntity<List<Submission>> getSubmissionByTaskId(
            @PathVariable Long taskId,
            @RequestHeader("Authorization") String jwt
    )throws Exception{
        UserDto user=userServices.getUserProfile(jwt);
        List<Submission> submissions=submissionServices.getTaskSubmissionByTaskId(taskId);
        return new ResponseEntity<>(submissions, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Submission> acceptOrDeclineSubmission(
            @PathVariable Long id,
            @RequestParam("status") String status,
            @RequestHeader("Authorization") String jwt
    )throws Exception{
        UserDto user=userServices.getUserProfile(jwt);
        Submission submission=submissionServices.acceptDeclineSubmission(id,status);
        return new ResponseEntity<>(submission,HttpStatus.CREATED);
    }

}
