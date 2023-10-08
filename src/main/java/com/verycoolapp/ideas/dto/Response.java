package com.verycoolapp.ideas.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class Response {
    private String status;
    private String error;
    private List<Object> data;
}
