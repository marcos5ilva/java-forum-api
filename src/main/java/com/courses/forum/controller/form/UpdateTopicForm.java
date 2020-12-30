package com.courses.forum.controller.form;

import com.courses.forum.module.Topic;
import com.courses.forum.repository.TopicRepository;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UpdateTopicForm {
    @NotNull
    @NotBlank
    @Size(min=5)
    private String title;

    @NotNull
    @NotBlank
    @Size(min=10)
    private String message;



    UpdateTopicForm(){}

    public Topic update(Long id, TopicRepository topicRepository) {
        Topic topic = topicRepository.getOne(id);
        topic.setTitle(this.title);
        topic.setMessage(this.message);

        return topic;
    }
}
