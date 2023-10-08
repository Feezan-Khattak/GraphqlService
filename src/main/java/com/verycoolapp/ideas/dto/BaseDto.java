package com.verycoolapp.ideas.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
public class BaseDto implements Serializable {
    private Long id;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
