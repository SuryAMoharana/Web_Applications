package com.task.services;

import com.task.model.Submission;
import com.task.model.TaskDto;
import com.task.repositories.SubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SubmissionServiceImplementations implements SubmissionServices{

    @Autowired
    private SubmissionRepository submissionRepository;

    @Autowired
    private TaskServices taskServices;

    @Override
    public Submission submitTask(Long taskId, String gitHubLink, Long userId, String jwt) throws Exception {
        TaskDto task=taskServices.getTaskById(taskId,jwt);
        if(task!=null){
            Submission submission=new Submission();
            submission.setTaskId(taskId);
            submission.setUserId(userId);
            submission.setGutHubLink(gitHubLink);
            submission.setSubmissionTime(LocalDateTime.now());
            return submissionRepository.save(submission);
        }
        throw new Exception("Task not found with id "+taskId);
    }

    @Override
    public Submission getTaskSubmissionById(Long submissionId) throws Exception {
        return submissionRepository.findById(submissionId).
                orElseThrow(()-> new Exception("Task Submission not found with id "+submissionId));
    }

    @Override
    public List<Submission> getAllTaskSubmission() {
        return submissionRepository.findAll();
    }

    @Override
    public List<Submission> getTaskSubmissionByTaskId(Long taskId) throws Exception {
        return submissionRepository.findByTaskId(taskId);
    }

    @Override
    public Submission acceptDeclineSubmission(Long id, String status) throws Exception {
        Submission submission=getTaskSubmissionById(id);
        submission.setStatus(status);
        if(status.equals("ACCEPT")){
            taskServices.completeTask(submission.getTaskId());
        }
        return submissionRepository.save(submission);
    }
}
