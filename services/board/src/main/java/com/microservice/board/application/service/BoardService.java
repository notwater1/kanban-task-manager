package com.microservice.board.application.service;

import com.microservice.board.application.dto.BoardResponse;
import com.microservice.board.application.dto.http.TaskResponse;
import com.microservice.board.application.exception.BoardNotFoundException;
import com.microservice.board.application.port.in.CreateBoardUseCase;
import com.microservice.board.application.port.in.GetBoardUseCase;
import com.microservice.board.application.port.out.BoardRepository;
import com.microservice.board.application.port.out.client.TaskServiceClient;
import com.microservice.board.domain.model.Board;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService implements CreateBoardUseCase, GetBoardUseCase {

    private final BoardRepository boardRepository;
    private final TaskServiceClient taskServiceClient;

    public BoardService(BoardRepository boardRepository, TaskServiceClient taskServiceClient) {
        this.boardRepository = boardRepository;
        this.taskServiceClient = taskServiceClient;
    }

    @Override
    public Board createBoard(Board board) {
        return this.boardRepository.save(board);
    }

    @Override
    public BoardResponse getBoardById(Long id) {
        Board board = this.boardRepository.findById(id)
                .orElseThrow(() -> new BoardNotFoundException("Board with ID " + id + " not found."));

        List<TaskResponse> tasks = this.taskServiceClient.getAllTasksByBoardId(id);

        return new BoardResponse(
                board.id(),
                board.name(),
                tasks
        );
    }
}
