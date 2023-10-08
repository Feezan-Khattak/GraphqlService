package com.verycoolapp.ideas.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IdeasDto extends BaseDto {
    private String ideaId;
    private String name;
    private String description;
}
