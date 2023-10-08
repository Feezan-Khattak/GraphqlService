package com.verycoolapp.ideas.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
public class BaseDto implements Serializable {
    private String id;
    private Date createdAt;
    private Date updatedAt;
}
