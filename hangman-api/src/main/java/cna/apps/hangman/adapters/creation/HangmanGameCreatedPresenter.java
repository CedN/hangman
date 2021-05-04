package cna.apps.hangman.adapters.creation;

import cna.apps.hangman.api.NewGameResponse;
import cna.apps.hangman.domain.ports.create.NewGame;
import cna.apps.hangman.domain.ports.create.NewGameOutputBoundary;

public class HangmanGameCreatedPresenter implements NewGameOutputBoundary {

  private NewGameResponse newGameResponse;

  @Override
  public void newGameCreated(NewGame newGame) {
    newGameResponse = new NewGameResponse();
    newGameResponse.gameId(newGame.getId().toString());
    newGameResponse.mask(newGame.getMask());
  }

  public NewGameResponse getNewGameResponse() {
    return newGameResponse;
  }

}
