package cna.apps.hangman.api;

import java.util.UUID;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import cna.apps.hangman.api.GameState.StateEnum;

public class GameHolder {

  private final String gameId;

  private final Game game;
  
  public GameHolder(Game game) {
    this.game = game;
    gameId = UUID.randomUUID().toString();
  }

  public String getGameId() {
    return gameId;
  }

  public boolean hasGameId(String gameId) {
    return this.gameId.equals(gameId);
  }
  
  public GetGameResponse computeGetGameResponse() {
    GetGameResponse getGameResponse = new GetGameResponse();
    GameState gameState = new GameState();
    gameState.setState(StateEnum.INPROGRESS);
    Hangman hangman = new Hangman();
    hangman.remainingTries(7);
    gameState.hangman(hangman);
    gameState.wordMask(game.getMask());
    getGameResponse.setGameState(gameState);
    getGameResponse.setUrlToSuggestLetter(ServletUriComponentsBuilder.fromCurrentRequest().build().toUri());
    return getGameResponse;
  }
}
