package com.courses.forum.controller.dto;

import com.courses.forum.module.Response;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ResponseDto {
    private Long id;
    private String message;
    private LocalDateTime creationDate;
    private String authorName;

    public ResponseDto(Response response){
        this.id = response.getId();
        this.message = response.getMessage();
        this.creationDate = response.getCreationDate();
        this.authorName = response.getAuthor().getName();
    }
}
