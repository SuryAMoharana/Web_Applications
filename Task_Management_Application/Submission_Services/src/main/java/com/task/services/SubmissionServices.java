package com.task.services;

import com.task.model.Submission;

import java.util.List;

public interface SubmissionServices {
    Submission submitTask(Long taskId, String gitHubLink, Long userId, String jwt) throws Exception;
    Submission getTaskSubmissionById(Long submissionId) throws Exception;
    List<Submission> getAllTaskSubmission();
    List<Submission> getTaskSubmissionByTaskId(Long taskId) throws Exception;
    Submission acceptDeclineSubmission(Long id, String status) throws Exception;
}
