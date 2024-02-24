package com.task.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskDto {
    private Long id;

    private String title;

    private String description;

    private String image;

    private Long assignedUserId;

    private List<String> tags=new ArrayList<>();

    private TaskStatus status;

    private LocalDateTime deadLine;

    private LocalDateTime createdAt;
}
