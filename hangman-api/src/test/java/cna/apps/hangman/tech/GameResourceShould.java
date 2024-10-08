/*
GameResourceShould.java
Copyright 2021 @CedN

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
package cna.apps.hangman.tech;

import static cna.apps.hangman.adapters.proposal.Errors.GAME_OVER;
import static cna.apps.hangman.adapters.proposal.Errors.UNKNOWN_GAME;
import static cna.apps.hangman.adapters.proposal.Errors.MessageKey.LOST_MESSAGE_KEY;
import static cna.apps.hangman.adapters.proposal.Errors.MessageKey.WON_MESSAGE_KEY;
import static cna.apps.hangman.api.ProposalResponse.GameStateEnum.INPROGRESS;
import static cna.apps.hangman.api.ProposalResponse.GameStateEnum.LOOSE;
import static cna.apps.hangman.api.ProposalResponse.GameStateEnum.WON;
import static cna.apps.hangman.tech.ErrorAssertions.assertError;
import static cna.apps.hangman.tech.ProposalResponseAssertions.assertProposalResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

  @Test
  void return_game_won_response() {
    String proposedLetter = "g";
    String message = "You won the game!";
    fakeProposeLetter.setGameWon(message, WORD_HANGMAN, INITIAL_HANGMAN_STEP);
    ResponseEntity<ProposalResponse> response = gameResource.proposeLetter(GAME_ID.toString(), proposedLetter);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertProposalResponse(response.getBody(), WORD_HANGMAN, INITIAL_HANGMAN_STEP, WON, message);
  }

  @Test
  void return_game_over_response_when_game_is_won() {
    String proposedLetter = "a";
    boolean isWonGame = true;
    fakeProposeLetter.setGameIsOver(isWonGame);
    BadRequestException exception = assertThrows(BadRequestException.class, () -> gameResource.proposeLetter(GAME_ID.toString(), proposedLetter));
    assertError(exception.getResponseEntity(), GAME_OVER.code(), GAME_OVER.message(WON_MESSAGE_KEY));
  }

  @Test
  void return_game_over_response_when_game_is_lost() {
    String proposedLetter = "a";
    boolean isWonGame = false;
    fakeProposeLetter.setGameIsOver(isWonGame);
    BadRequestException exception = assertThrows(BadRequestException.class, () -> gameResource.proposeLetter(GAME_ID.toString(), proposedLetter));
    assertError(exception.getResponseEntity(), GAME_OVER.code(), GAME_OVER.message(LOST_MESSAGE_KEY));
  }

  @Test
  void return_unknown_game_when_the_game_does_not_exist() {
    String proposedLetter = "a";
    fakeProposeLetter.setUnknownGame();
    BadRequestException exception = assertThrows(BadRequestException.class, () -> gameResource.proposeLetter(GAME_ID.toString(), proposedLetter));
    assertError(exception.getResponseEntity(), UNKNOWN_GAME.code(), UNKNOWN_GAME.message());
  }
  
}
