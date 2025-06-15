package com.microservice.board.presentation.controller;

import com.microservice.board.application.dto.BoardResponse;
import com.microservice.board.application.port.in.CreateBoardUseCase;
import com.microservice.board.application.port.in.GetBoardUseCase;
import com.microservice.board.domain.model.Board;
import com.microservice.board.presentation.controller.dto.BoardRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/boards")
public class BoardController {

    private final CreateBoardUseCase createBoardUseCase;
    private final GetBoardUseCase getBoardUseCase;

    public BoardController(CreateBoardUseCase createBoardUseCase, GetBoardUseCase getBoardUseCase) {
        this.createBoardUseCase = createBoardUseCase;
        this.getBoardUseCase = getBoardUseCase;
    }

    @PostMapping
    public ResponseEntity<Board> createBoard(@Valid @RequestBody BoardRequest boardRequest){
        Board board = new Board(null, boardRequest.name());
        Board savedBoard = this.createBoardUseCase.createBoard(board);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBoard);

    }

    @GetMapping("/{id}")
    public ResponseEntity<BoardResponse> getBoardById(@PathVariable Long id){
        BoardResponse board = this.getBoardUseCase.getBoardById(id);
        return ResponseEntity.ok(board);
    }
}
