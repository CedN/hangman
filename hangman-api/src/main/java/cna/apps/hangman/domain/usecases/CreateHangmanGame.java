package cna.apps.hangman.domain.usecases;

import java.util.UUID;

import cna.apps.hangman.domain.core.HangmanGame;
import cna.apps.hangman.domain.ports.WordProvider;
import cna.apps.hangman.domain.ports.create.CreateHangmanGameInputBoundary;
import cna.apps.hangman.domain.ports.create.NewGame;
import cna.apps.hangman.domain.ports.create.NewGameOutputBoundary;

public class CreateHangmanGame implements CreateHangmanGameInputBoundary {

  private final WordProvider wordProvider;
  private final NewGameOutputBoundary presenter;

  public CreateHangmanGame(WordProvider wordProvider, NewGameOutputBoundary presenter) {
    this.wordProvider = wordProvider;
    this.presenter = presenter;
  }

  @Override
  public void startNewGame() {
    var hangmanGame = new HangmanGame(wordProvider.provide());
    presenter.newGameCreated(new NewGame(UUID.randomUUID().toString(), hangmanGame.getMask()));
  }

}
