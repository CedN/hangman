package cna.apps.hangman.adapters;

import java.util.HashMap;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cna.apps.hangman.domain.entities.HangmanGame;
import cna.apps.hangman.domain.ports.GameRepository;

public class InMemoryGameRepository implements GameRepository {

  private static final Logger LOGGER = LoggerFactory.getLogger(InMemoryGameRepository.class);

  private HashMap<UUID, HangmanGame> games = new HashMap<>();

  public void add(UUID gameId, HangmanGame hangmanGame) {
    games.put(gameId, hangmanGame);
    LOGGER.debug("The hangman game '{}' has been added with the ID = '{}'",
        hangmanGame.getWordToGuess().getValue(), gameId);
  }

  @Override
  public HangmanGame get(UUID gameId) {
    HangmanGame hangmanGame = games.get(gameId);
    LOGGER.debug("The ID = '{}' matches the hangman game '{}'", gameId, hangmanGame);
    return hangmanGame;
  }

}
