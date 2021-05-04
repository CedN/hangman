package cna.apps.hangman.domain.usecases.proposal;

import static cna.apps.hangman.domain.usecases.GameFixtures.givenNewGame;
import static cna.apps.hangman.domain.usecases.WordFixtures.HANGMAN;
import static cna.apps.hangman.domain.usecases.WordFixtures.HANGMAN_MASK;
import static cna.apps.hangman.domain.usecases.WordFixtures.HANGMAN_MASK_WITH_A;
import static cna.apps.hangman.domain.usecases.WordFixtures.HANGMAN_MASK_WITH_H;
import static cna.apps.hangman.domain.usecases.proposal.LetterProposalAssertions.assertProgressingGame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static cna.apps.hangman.domain.usecases.proposal.LetterProposalAssertions.assertLostGame;

import java.util.Arrays;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cna.apps.hangman.domain.ports.GameRepository;
import cna.apps.hangman.domain.usecases.ProposeLetter;
import cna.apps.hangman.provider.InMemoryGameRepository;

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
  void add_letter_h_to_the_mask_if_the_word_to_guess_is_hangman() {
    var gameId = givenNewGame(gameRepository, HANGMAN);
    proposeLetter.tryLetter(gameId, 'h');
    assertProgressingGame(presenter.getProgressingGame(), HANGMAN_MASK_WITH_H, EMPTY_HANGMAN);
  }

  @Test
  void add_all_letters_a_to_the_mask_if_the_word_to_guess_is_hangman() {
    var gameId = givenNewGame(gameRepository, HANGMAN);
    proposeLetter.tryLetter(gameId, 'a');
    assertProgressingGame(presenter.getProgressingGame(), HANGMAN_MASK_WITH_A, EMPTY_HANGMAN);
  }

  @Test
  void let_the_mask_unchanged_and_set_hangman_at_step_1_when_propose_letter_z_if_the_word_to_guess_is_hangman() {
    var gameId = givenNewGame(gameRepository, HANGMAN);
    proposeLetter.tryLetter(gameId, 'z');
    assertProgressingGame(presenter.getProgressingGame(), HANGMAN_MASK, HANGMAN_AT_STEP_1);
  }

  @Test
  void increased_hangman_when_propose_a_good_letter_already_proposed() {
    var gameId = givenNewGame(gameRepository, HANGMAN);
    proposeLetters(gameId, 'a', 'a');
    assertProgressingGame(presenter.getProgressingGame(), HANGMAN_MASK_WITH_A, HANGMAN_AT_STEP_1);
  }

  private void proposeLetters(UUID gameId, Character... letters) {
    Arrays.asList(letters).stream().forEach(c -> proposeLetter.tryLetter(gameId, c));
  }

  @Test
  void indicate_the_game_is_lose_after_7_wrong_letter_proposal() {
    var gameId = givenNewGame(gameRepository, HANGMAN);
    proposeLetters(gameId, 'q', 'w', 'e', 'r', 't', 'y', 'u');
    var lostGame = presenter.getLostGame();
    assertLostGame(lostGame, HANGMAN_MASK, FULL_HANGMAN, HANGMAN);
  }

  @Test
  void indicate_the_game_is_won_if_all_letters_are_been_discovered() {
    var gameId = givenNewGame(gameRepository, HANGMAN);
    proposeLetters(gameId, 'h', 'a', 'n', 'g', 'm');
    var wonGame = presenter.getWonGame();
    assertEquals(HANGMAN, wonGame.getMask());
    assertTrue(wonGame.getHangmanStep() < FULL_HANGMAN);
  }

  @Test
  void indicate_the_game_is_over_if_game_is_won_and_propose_a_letter() {
    var gameId = givenWonGame();
    proposeLetter.tryLetter(gameId, 'a');
    var gameOver = presenter.getGameOver();
    assertTrue(gameOver.isWonGame());
  }

  private UUID givenWonGame() {
    var gameId = givenNewGame(gameRepository, HANGMAN);
    proposeLetters(gameId, 'h', 'a', 'n', 'g', 'm');
    return gameId;
  }

  @Test
  void indicate_the_game_is_over_if_game_is_lost_and_propose_a_letter() {
    var gameId = givenLostGame();
    proposeLetter.tryLetter(gameId, 'a');
    var gameOver = presenter.getGameOver();
    assertFalse(gameOver.isWonGame());
  }

  private UUID givenLostGame() {
    var gameId = givenNewGame(gameRepository, HANGMAN);
    proposeLetters(gameId, 'q', 'w', 'e', 'r', 't', 'y', 'u');
    return gameId;
  }

}