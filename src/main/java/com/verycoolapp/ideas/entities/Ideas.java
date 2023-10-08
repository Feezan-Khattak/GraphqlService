package com.verycoolapp.ideas.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;

@Setter
@Getter
@Document(collation = "ideas")
public class Ideas{
    @Id
    private Long id;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String ideaId;
    private String name;
    private String description;
    private String tag;
    private String image;
    private String comment;
    private Long likes;
}
