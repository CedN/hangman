package cna.apps.hangman.api;

import java.net.URI;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class GameResource implements GameApi {

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
  
}
