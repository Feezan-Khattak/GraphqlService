package com.verycoolapp.ideas.util;

import com.verycoolapp.ideas.dto.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class ResponseUtil {

    public Response generateSuccessResponse(Object data) {
        return Response.builder()
                .status("OK")
                .data(List.of(data))
                .build();
    }

    public Response generateFailureResponse(String error){
        return Response.builder()
                .status("FAIL")
                .error(error)
                .build();
    }
}
