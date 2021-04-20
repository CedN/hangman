package cna.apps.hangman.api;

import java.util.ArrayList;
import java.util.List;

public class GameRepository {

  private final List<GameHolder> gameHolders = new ArrayList<>();
  
  public void addGameHolder(GameHolder gameHolder) {
    gameHolders.add(gameHolder);
  }

  public GameHolder getGameHolder(String gameId) {
    return gameHolders.stream().filter(gameHolder -> gameHolder.hasGameId(gameId)).findFirst().orElse(null);
  }
  
}
