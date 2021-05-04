package cna.apps.hangman.tech;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import cna.apps.hangman.adapters.creation.HangmanGameCreatedPresenter;
import cna.apps.hangman.adapters.creation.HangmanGameCreationController;
import cna.apps.hangman.api.NewGameResponse;

public class GameResourceShould {

  private static final String MASK = "----";
  private static final UUID GAME_ID = UUID.randomUUID();

  private HangmanGameCreationController controller;
  private HangmanGameCreatedPresenter presenter;
  private FakeCreateHangmanGame fakeUsecase;

  @BeforeEach
  void setUp() {
    presenter = new HangmanGameCreatedPresenter();
    fakeUsecase = new FakeCreateHangmanGame(presenter);
    controller = new HangmanGameCreationController(fakeUsecase);
  }

  @Test
  void return_new_game() {
    fakeUsecase.setNewGameValues(GAME_ID, MASK);
    ResponseEntity<NewGameResponse> response = new GameResource(controller, presenter).createGame();
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNewGameResponse(response.getBody(), GAME_ID, MASK);
  }

  private void assertNewGameResponse(NewGameResponse newGameResponse, UUID gameId, String mask) {
    assertEquals(gameId.toString(), newGameResponse.getGameId());
    assertEquals(mask, newGameResponse.getMask());
  }
  
}
