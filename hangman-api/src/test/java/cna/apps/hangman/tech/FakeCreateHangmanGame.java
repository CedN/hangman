package cna.apps.hangman.tech;

import java.util.UUID;

import cna.apps.hangman.adapters.creation.HangmanGameCreatedPresenter;
import cna.apps.hangman.domain.ports.create.CreateHangmanGameInputBoundary;
import cna.apps.hangman.domain.ports.create.NewGame;

public class FakeCreateHangmanGame implements CreateHangmanGameInputBoundary {

  private final HangmanGameCreatedPresenter presenter;
  private UUID gameId;
  private String mask;

  public FakeCreateHangmanGame(HangmanGameCreatedPresenter presenter) {
    this.presenter = presenter;
  }

  public void setNewGameValues(UUID gameId, String mask) {
    this.gameId = gameId;
    this.mask = mask;
  }

  @Override
  public void startNewGame() {
    presenter.newGameCreated(new NewGame(gameId, mask));
  }
  
}
