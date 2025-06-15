package com.microservice.board.infrastructure.persistence;

import com.microservice.board.application.port.out.BoardRepository;
import com.microservice.board.domain.model.Board;
import com.microservice.board.infrastructure.mapper.BoardMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JpaBoardRepositoryAdapter implements BoardRepository {

    private final BoardPersistenceRepository boardPersistenceRepository;

    public JpaBoardRepositoryAdapter(BoardPersistenceRepository boardPersistenceRepository) {
        this.boardPersistenceRepository = boardPersistenceRepository;
    }

    @Override
    public Board save(Board board) {
        BoardEntity boardEntity = BoardMapper.toPersistence(board);
        BoardEntity savedBoard = this.boardPersistenceRepository.save(boardEntity);
        return BoardMapper.toDomain(savedBoard);
    }

    @Override
    public Optional<Board> findById(Long id) {
        return this.boardPersistenceRepository.findById(id).map(BoardMapper::toDomain);
    }

}
