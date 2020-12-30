package com.courses.forum.config.validation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class FormErrorDto {
    private String field;
    private String error;

    public FormErrorDto(String field, String error) {
        this.field = field;
        this.error = error;
    }
}
