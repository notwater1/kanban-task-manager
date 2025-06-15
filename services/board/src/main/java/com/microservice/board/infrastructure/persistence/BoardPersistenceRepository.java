package com.microservice.board.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardPersistenceRepository extends JpaRepository<BoardEntity, Long> {
}
