package cna.apps.hangman.tech;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import cna.apps.hangman.adapters.creation.HangmanGameCreatedPresenter;
import cna.apps.hangman.adapters.creation.HangmanGameCreationController;
import cna.apps.hangman.api.GameApi;
import cna.apps.hangman.api.NewGameResponse;

@RestController
public class GameResource implements GameApi {

  private final HangmanGameCreationController controller;
  private final HangmanGameCreatedPresenter presenter;

  public GameResource(HangmanGameCreationController controller, HangmanGameCreatedPresenter presenter) {
    this.controller = controller;
    this.presenter = presenter;
  }

  @Override
  public ResponseEntity<NewGameResponse> createGame() {
    controller.createNewGame();
    return ResponseEntity.ok(presenter.getNewGameResponse());
  }
  
}
