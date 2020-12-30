package com.courses.forum.controller.dto;

import com.courses.forum.module.Topic;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class TopicDTO {
    private Long id;
    private String title;
    private String message;
    private LocalDateTime creationDate;

    public TopicDTO(Topic topic){
        this.id = topic.getId();
        this.title = topic.getTitle();
        this.message = topic.getMessage();
        this.creationDate = topic.getCreationDate();
    }

    public static Page<TopicDTO> converter(Page<Topic> topics) {
        return topics.map(TopicDTO::new);
    }
}
