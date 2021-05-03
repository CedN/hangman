package cna.apps.hangman.domain.usecases;

import java.util.UUID;

import cna.apps.hangman.domain.core.HangmanGame;
import cna.apps.hangman.domain.ports.GameRepository;

public class GameFixtures {

  public static UUID givenNewGame(GameRepository gameRepository, String word) {
    var gameId = UUID.randomUUID();
    gameRepository.add(gameId, new HangmanGame(word));
    return gameId;
  }
  
}
