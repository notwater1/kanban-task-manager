package com.microservice.board.application.port.in;

import com.microservice.board.application.dto.BoardResponse;

public interface GetBoardUseCase {
    BoardResponse getBoardById(Long id);
}
