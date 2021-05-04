package cna.apps.hangman.domain.ports;

import java.util.UUID;

import cna.apps.hangman.domain.entities.HangmanGame;

public interface GameRepository {

	HangmanGame get(UUID gameId);

	void add(UUID gameId, HangmanGame hangmanGame);

}
