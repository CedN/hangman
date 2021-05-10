package cna.apps.hangman.adapters.creation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cna.apps.hangman.domain.ports.create.CreateHangmanGameInputBoundary;

public class HangmanGameCreationController {

  private static final Logger LOGGER = LoggerFactory.getLogger(HangmanGameCreationController.class);

  private CreateHangmanGameInputBoundary usecase;

  public HangmanGameCreationController(CreateHangmanGameInputBoundary usecase) {
    this.usecase = usecase;
  }

  public void createNewGame() {
    LOGGER.info("Start a new game");
    usecase.startNewGame();
  }
  
}
