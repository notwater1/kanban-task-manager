package com.microservice.board.application.port.in;

import com.microservice.board.domain.model.Board;

public interface CreateBoardUseCase {
    Board createBoard(Board board);
}
