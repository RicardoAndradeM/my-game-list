package io.github.ricardoandradem.mgl.controller;

import io.github.ricardoandradem.mgl.dto.GameStatusDTO;
import io.github.ricardoandradem.mgl.model.Game;
import io.github.ricardoandradem.mgl.services.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/users/{userId}/games")
public class GameController {

    private final GameService service;

    public GameController(GameService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Game> createGame(@PathVariable Long userId, @RequestBody Game gameToCreate) {
        var gameCreated = service.createGame(userId, gameToCreate);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(gameCreated.getId())
                .toUri();
        return ResponseEntity.created(location).body(gameCreated);
    }

    @PatchMapping("/{gameId}/status")
    public ResponseEntity<Game> updateGameStatus(
            @PathVariable Long userId,
            @PathVariable Long gameId,
            @RequestBody GameStatusDTO gameStatusDTO
    ) {
        var game = service.updateGameStatus(userId, gameId, gameStatusDTO);
        return ResponseEntity.ok(game);
    }
}
