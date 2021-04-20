package cna.apps.hangman.api;

import java.net.URI;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class GameResource implements GameApi {

  private final GameRepository gameRepository;

  public GameResource(GameRepository gameRepository) {
    this.gameRepository = gameRepository;
  }

  @Override
  public ResponseEntity<Void> createGame() {
    URI location = computeNewGameLocation();
    return ResponseEntity.created(location).build();
  }

  private URI computeNewGameLocation() {
    return ServletUriComponentsBuilder
      .fromCurrentRequest()
      .path("/{gameId}")
      .buildAndExpand(UUID.randomUUID())
      .toUri();
  }

  @Override
  public ResponseEntity<GetGameResponse> fetchGame(String gameId) {
    var gameHolder = gameRepository.getGameHolder(gameId);
    if (gameHolder == null) {
      return ResponseEntity.badRequest().build();
    }
    return ResponseEntity.ok(gameHolder.computeGetGameResponse());
  }
  
}
