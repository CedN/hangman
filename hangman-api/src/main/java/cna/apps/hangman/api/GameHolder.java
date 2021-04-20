package cna.apps.hangman.api;

import java.util.UUID;

public class GameHolder {

  private String gameId;

  public GameHolder() {
    gameId = UUID.randomUUID().toString();
  }

  public String getGameId() {
    return gameId;
  }

  public boolean hasGameId(String gameId) {
    return this.gameId.equals(gameId);
  }
  
  public GetGameResponse computeGetGameResponse() {
    return new GetGameResponse();
  }
}
