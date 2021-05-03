package cna.apps.hangman.provider;

import java.util.HashMap;
import java.util.UUID;

import cna.apps.hangman.domain.core.HangmanGame;
import cna.apps.hangman.domain.ports.GameRepository;

public class InMemoryGameRepository implements GameRepository {

	private HashMap<UUID, HangmanGame> games = new HashMap<>();

  public void add(UUID gameId, HangmanGame hangmanGame) {
    games.put(gameId, hangmanGame);
	}

  @Override
  public HangmanGame get(UUID gameId) {
    return games.get(gameId);
  }

}
