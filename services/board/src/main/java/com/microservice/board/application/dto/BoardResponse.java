package com.microservice.board.application.dto;

import com.microservice.board.application.dto.http.TaskResponse;

import java.util.List;

public record BoardResponse (
        Long id,
        String name,
        List<TaskResponse> tasks
) {
}
