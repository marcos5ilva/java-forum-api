package com.courses.forum.controller.dto;

import com.courses.forum.module.StatusTopic;
import com.courses.forum.module.Topic;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class DetailedTopicDTO {
    private Long id;
    private String title;
    private String message;
    private LocalDateTime creationDate;
    private String authorName;
    private StatusTopic status;
    private List<ResponseDto> responses;

    public DetailedTopicDTO(Topic topic){
        this.id = topic.getId();
        this.title = topic.getTitle();
        this.message = topic.getMessage();
        this.creationDate = topic.getCreationDate();
        this.authorName = topic.getAuthor().getName();
        this.status = topic.getStatus();
        this.responses = new ArrayList<>();
        this.responses.addAll(topic.getRespostas().stream().map(ResponseDto::new).collect(Collectors.toList()));
    }

}
