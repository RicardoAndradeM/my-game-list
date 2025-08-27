package io.github.ricardoandradem.mgl.services;

import io.github.ricardoandradem.mgl.dto.GameStatusDTO;
import io.github.ricardoandradem.mgl.model.Game;
import io.github.ricardoandradem.mgl.model.GameStatus;
import io.github.ricardoandradem.mgl.repository.GameRepository;
import io.github.ricardoandradem.mgl.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private final UserRepository userRepository;

    public GameService(GameRepository gameRepository, UserRepository userRepository) {
        this.gameRepository = gameRepository;
        this.userRepository = userRepository;
    }

    public Game createGame(Long userId, Game game) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado."));
        game.setUser(user);
        return gameRepository.save(game);
    }

    public Game updateGameStatus(Long userId, Long gameId, GameStatusDTO gameStatusDTO) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado."));
        var game = gameRepository.findById(gameId)
                .orElseThrow(() -> new RuntimeException("Jogo não encontrado."));

        if(!game.getUser().getId().equals(userId)) {
            throw new RuntimeException("Jogo não pertece ao usuario");
        }

        game.setStatus(gameStatusDTO.status());
        return gameRepository.save(game);
    }
}
