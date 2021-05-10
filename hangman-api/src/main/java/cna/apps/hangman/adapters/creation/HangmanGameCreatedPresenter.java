package cna.apps.hangman.adapters.creation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cna.apps.hangman.api.NewGameResponse;
import cna.apps.hangman.domain.ports.create.NewGame;
import cna.apps.hangman.domain.ports.create.NewGameOutputBoundary;

public class HangmanGameCreatedPresenter implements NewGameOutputBoundary {

  private static final Logger LOGGER = LoggerFactory.getLogger(HangmanGameCreatedPresenter.class);

  private NewGameResponse newGameResponse;

  @Override
  public void newGameCreated(NewGame newGame) {
    newGameResponse = new NewGameResponse();
    newGameResponse.gameId(newGame.getId().toString());
    newGameResponse.mask(newGame.getMask());
  }

  public NewGameResponse getNewGameResponse() {
    LOGGER.info("New game started with ID = '{}'", newGameResponse.getGameId());
    return newGameResponse;
  }

}
