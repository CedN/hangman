package cna.apps.hangman.tech;

import static cna.apps.hangman.api.ProposalResponse.GameStateEnum.INPROGRESS;
import static cna.apps.hangman.api.ProposalResponse.GameStateEnum.LOOSE;
import static cna.apps.hangman.tech.ProposalResponseAssertions.assertProposalResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import cna.apps.hangman.adapters.creation.HangmanGameCreatedPresenter;
import cna.apps.hangman.adapters.creation.HangmanGameCreationController;
import cna.apps.hangman.adapters.proposal.LetterProposalController;
import cna.apps.hangman.adapters.proposal.LetterProposedPresenter;
import cna.apps.hangman.api.NewGameResponse;
import cna.apps.hangman.api.ProposalResponse;

public class GameResourceShould {

  private static final String WORD_HANGMAN = "hangman";
  private static final String MASKED_WORD_HANGMAN = "-------";
  private static final String MASKED_WORD_HANGMAN_WITH_A_DISCOVERED = "-a---a-";
  private static final UUID GAME_ID = UUID.randomUUID();
  private static final int INITIAL_HANGMAN_STEP = 0;
  private static final int FINAL_HANGMAN_STEP = 7;

  private HangmanGameCreationController hangmanGameCreationController;
  private HangmanGameCreatedPresenter hangmanGameCreatedPresenter;
  private FakeCreateHangmanGame fakeCreateHangmanGame;
  private LetterProposalController letterProposalController;
  private LetterProposedPresenter letterProposedPresenter;
  private FakeProposeLetter fakeProposeLetter;
  private GameResource gameResource;

  @BeforeEach
  void setUp() {
    hangmanGameCreatedPresenter = new HangmanGameCreatedPresenter();
    fakeCreateHangmanGame = new FakeCreateHangmanGame(hangmanGameCreatedPresenter);
    hangmanGameCreationController = new HangmanGameCreationController(fakeCreateHangmanGame);
    letterProposedPresenter = new LetterProposedPresenter();
    fakeProposeLetter = new FakeProposeLetter(letterProposedPresenter);
    letterProposalController = new LetterProposalController(fakeProposeLetter);
    gameResource = new GameResource(hangmanGameCreationController, hangmanGameCreatedPresenter, letterProposalController, letterProposedPresenter);
  }

  @Test
  void return_new_game() {
    fakeCreateHangmanGame.setNewGameValues(GAME_ID, MASKED_WORD_HANGMAN);
    ResponseEntity<NewGameResponse> response = gameResource.createGame();
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNewGameResponse(response.getBody(), GAME_ID, MASKED_WORD_HANGMAN);
  }

  private void assertNewGameResponse(NewGameResponse newGameResponse, UUID gameId, String mask) {
    assertEquals(gameId.toString(), newGameResponse.getGameId());
    assertEquals(mask, newGameResponse.getMask());
  }

  @Test
  void return_game_in_progess_response() {
    String proposedLetter = "a";
    String message = "You have found the letter '" + proposedLetter + "'";
    fakeProposeLetter.setGameInProgressValues(message, MASKED_WORD_HANGMAN_WITH_A_DISCOVERED, INITIAL_HANGMAN_STEP);
    ResponseEntity<ProposalResponse> response = gameResource.proposeLetter(GAME_ID.toString(), proposedLetter);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertProposalResponse(response.getBody(), MASKED_WORD_HANGMAN_WITH_A_DISCOVERED, INITIAL_HANGMAN_STEP, INPROGRESS, message);
  }

  @Test
  void return_game_lost_reponse() {
    String proposedLetter = "z";
    String message = "You have lost! The word to guess was '" + WORD_HANGMAN + "'";
    fakeProposeLetter.setGameLost(message, WORD_HANGMAN, MASKED_WORD_HANGMAN);
    ResponseEntity<ProposalResponse> response = gameResource.proposeLetter(GAME_ID.toString(), proposedLetter);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertProposalResponse(response.getBody(), MASKED_WORD_HANGMAN, FINAL_HANGMAN_STEP, LOOSE, message);
  }
  
}
