package com.verycoolapp.ideas.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import java.util.Date;

@Setter
@Getter
public class Ideas{
    @Id
    private String id;
    private Date createdAt;
    private Date updatedAt;
    private String ideaId;
    private String name;
    private String description;
    private String tag;
    private String image;
    private String comment;
    private Long likes;
}
