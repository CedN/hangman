package cna.apps.hangman.adapters.creation;

import cna.apps.hangman.domain.ports.create.CreateHangmanGameInputBoundary;

public class HangmanGameCreationController {

  private CreateHangmanGameInputBoundary usecase;

  public HangmanGameCreationController(CreateHangmanGameInputBoundary usecase) {
    this.usecase = usecase;
  }

  public void createNewGame() {
    usecase.startNewGame();
  }
  
}
