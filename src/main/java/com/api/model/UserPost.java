package com.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserPost {
    private Integer userId;
    private Integer id;
    private String title;
    private String body;
}
