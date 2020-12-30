package com.courses.forum.controller.form;

import com.courses.forum.module.Course;
import com.courses.forum.module.Topic;
import com.courses.forum.repository.CourseRepository;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TopicForm {

    @NotNull
    @NotBlank
    @Size(min=5)
    private String title;

    @NotNull
    @NotBlank
    @Size(min=10)
    private String message;

    @NotNull
    @NotBlank
    private String courseName;

    TopicForm(){}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Topic convert(CourseRepository repository) {
        Course course = repository.findByName(courseName);
        return new Topic(title, message, course);
    }
}
