package cna.apps.hangman.domain.usecases.create;

import cna.apps.hangman.domain.ports.create.NewGame;
import cna.apps.hangman.domain.ports.create.NewGameOutputBoundary;

public class NewGamePresenterSpy implements NewGameOutputBoundary {

  private NewGame newGame;

  @Override
  public void newGameCreated(NewGame newGame) {
      this.newGame = newGame;
  }

  public NewGame getNewGame() {
    return newGame;
  }

}
