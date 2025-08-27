package io.github.ricardoandradem.mgl.repository;

import io.github.ricardoandradem.mgl.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}
