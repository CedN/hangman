package cna.apps.hangman.domain.usecases;

import java.util.UUID;

import cna.apps.hangman.domain.entities.HangmanGame;
import cna.apps.hangman.domain.ports.GameRepository;
import cna.apps.hangman.domain.ports.WordProvider;
import cna.apps.hangman.domain.ports.create.CreateHangmanGameInputBoundary;
import cna.apps.hangman.domain.ports.create.NewGame;
import cna.apps.hangman.domain.ports.create.NewGameOutputBoundary;

public class CreateHangmanGame implements CreateHangmanGameInputBoundary {

  private final WordProvider wordProvider;
  private final NewGameOutputBoundary presenter;
  private final GameRepository gameRepository;

  public CreateHangmanGame(WordProvider wordProvider, GameRepository gameRepository, NewGameOutputBoundary presenter) {
    this.wordProvider = wordProvider;
    this.gameRepository = gameRepository;
    this.presenter = presenter;
  }

  @Override
  public void startNewGame() {
    var hangmanGame = new HangmanGame(wordProvider.provide());
    UUID gameId = UUID.randomUUID();
    gameRepository.add(gameId, hangmanGame);
    presenter.newGameCreated(new NewGame(gameId, hangmanGame.getMask()));
  }

}
