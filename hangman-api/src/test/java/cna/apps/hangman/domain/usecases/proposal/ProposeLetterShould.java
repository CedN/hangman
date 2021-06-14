/*
ProposeLetterShould.java
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
package cna.apps.hangman.domain.usecases.proposal;

import static cna.apps.hangman.domain.usecases.GameFixtures.givenNewGame;
import static cna.apps.hangman.domain.usecases.WordFixtures.HANGMAN;
import static cna.apps.hangman.domain.usecases.WordFixtures.HANGMAN_MASK;
import static cna.apps.hangman.domain.usecases.WordFixtures.HANGMAN_MASK_WITH_A;
import static cna.apps.hangman.domain.usecases.WordFixtures.HANGMAN_MASK_WITH_H;
import static cna.apps.hangman.domain.usecases.proposal.LetterProposalAssertions.assertLostGame;
import static cna.apps.hangman.domain.usecases.proposal.LetterProposalAssertions.assertProgressingGame;
import static cna.apps.hangman.domain.usecases.proposal.LetterProposalAssertions.assertWonGame;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cna.apps.hangman.adapters.InMemoryGameRepository;
import cna.apps.hangman.domain.entities.MoveResult;
import cna.apps.hangman.domain.entities.WordToGuess;
import cna.apps.hangman.domain.ports.GameRepository;
import cna.apps.hangman.domain.usecases.ProposeLetter;
import cna.apps.hangman.domain.usecases.UnknownGameException;

public class ProposeLetterShould {
  /**
   * Empty hangman is at step '0'
   */
  private static final int EMPTY_HANGMAN = 0;
  private static final int HANGMAN_AT_STEP_1 = 1;
  private static final int FULL_HANGMAN = 7;
  private ProposeLetter proposeLetter;
  private GameRepository gameRepository;
  private LetterProposalPresenterSpy presenter;

  @BeforeEach
  void setUp() {
    gameRepository = new InMemoryGameRepository();
    presenter = new LetterProposalPresenterSpy();
    proposeLetter = new ProposeLetter(gameRepository, presenter);
  }

  @Test
  void throw_UnknowGameException_if_the_game_id_does_not_match_any_game() {
    char proposedLetter = 'h';
    UUID randomGameId = UUID.randomUUID();
    assertThrows(UnknownGameException.class, () -> proposeLetter.tryLetter(randomGameId, proposedLetter));
  }

  @Test
  void add_letter_h_to_the_mask_if_the_word_to_guess_is_hangman() throws UnknownGameException {
    char proposedLetter = 'h';
    var gameId = givenNewGame(gameRepository, HANGMAN);
    proposeLetter.tryLetter(gameId, proposedLetter);
    assertProgressingGame(presenter.getProgressingGame(), HANGMAN_MASK_WITH_H, EMPTY_HANGMAN, MoveResult.GOOD, proposedLetter);
  }

  @Test
  void add_all_letters_a_to_the_mask_if_the_word_to_guess_is_hangman() throws UnknownGameException {
    char proposedLetter = 'a';
    var gameId = givenNewGame(gameRepository, HANGMAN);
    proposeLetter.tryLetter(gameId, proposedLetter);
    assertProgressingGame(presenter.getProgressingGame(), HANGMAN_MASK_WITH_A, EMPTY_HANGMAN, MoveResult.GOOD, proposedLetter);
  }

  @Test
  void let_the_mask_unchanged_and_set_hangman_at_step_1_when_propose_letter_z_if_the_word_to_guess_is_hangman() throws UnknownGameException {
    char proposedLetter = 'z';
    var gameId = givenNewGame(gameRepository, HANGMAN);
    proposeLetter.tryLetter(gameId, proposedLetter);
    assertProgressingGame(presenter.getProgressingGame(), HANGMAN_MASK, HANGMAN_AT_STEP_1, MoveResult.WRONG, proposedLetter);
  }

  @Test
  void increased_hangman_when_propose_a_good_letter_already_proposed() throws UnknownGameException {
    char proposedLetter = 'a';
    var gameId = givenNewGame(gameRepository, HANGMAN);
    proposeLetters(gameId, proposedLetter, proposedLetter);
    assertProgressingGame(presenter.getProgressingGame(), HANGMAN_MASK_WITH_A, HANGMAN_AT_STEP_1, MoveResult.WRONG, proposedLetter);
  }

  private void proposeLetters(UUID gameId, Character... letters) throws UnknownGameException {
    for (Character letter: letters) {
      proposeLetter.tryLetter(gameId, letter);
    }
  }

  @Test
  void lose_the_game_after_7_wrong_letter_proposals() throws UnknownGameException {
    char proposedLetter = 'u';
    var gameId = givenNewGame(gameRepository, HANGMAN);
    proposeLetters(gameId, 'q', 'w', 'e', 'r', 't', 'y', proposedLetter);
    var lostGame = presenter.getLostGame();
    assertLostGame(lostGame, HANGMAN_MASK, FULL_HANGMAN, new WordToGuess(HANGMAN), proposedLetter);
  }

  @Test
  void win_the_game_after_all_letters_are_been_discovered() throws UnknownGameException {
    var gameId = givenNewGame(gameRepository, HANGMAN);
    proposeLetters(gameId, 'h', 'a', 'n', 'g', 'm');
    var wonGame = presenter.getWonGame();
    assertWonGame(wonGame, new WordToGuess(HANGMAN)); 
  }

  @Test
  void game_over_if_propose_a_letter_when_game_is_won() throws UnknownGameException {
    var gameId = givenWonGame();
    proposeLetter.tryLetter(gameId, 'a');
    var gameOver = presenter.getGameOver();
    assertTrue(gameOver.isWonGame());
  }

  private UUID givenWonGame() throws UnknownGameException {
    var gameId = givenNewGame(gameRepository, HANGMAN);
    proposeLetters(gameId, 'h', 'a', 'n', 'g', 'm');
    return gameId;
  }

  @Test
  void game_over_if_propose_a_letter_when_game_is_lost() throws UnknownGameException {
    var gameId = givenLostGame();
    proposeLetter.tryLetter(gameId, 'a');
    var gameOver = presenter.getGameOver();
    assertFalse(gameOver.isWonGame());
  }

  private UUID givenLostGame() throws UnknownGameException {
    var gameId = givenNewGame(gameRepository, HANGMAN);
    proposeLetters(gameId, 'q', 'w', 'e', 'r', 't', 'y', 'u');
    return gameId;
  }

}
