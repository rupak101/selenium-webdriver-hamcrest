package com.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Comment {
    private Integer postId;
    private Integer id;
    private String name;
    private String email;
    private String body;
}