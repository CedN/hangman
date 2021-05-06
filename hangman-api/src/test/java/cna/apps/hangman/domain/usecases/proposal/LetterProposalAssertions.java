package cna.apps.hangman.domain.usecases.proposal;

import static cna.apps.hangman.domain.entities.MoveResult.LOST_GAME;
import static cna.apps.hangman.domain.entities.MoveResult.WON_GAME;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import cna.apps.hangman.domain.ports.proposal.ProgressingGame;
import cna.apps.hangman.domain.ports.proposal.WonGame;
import cna.apps.hangman.domain.usecases.MoveResultMessageFactory;
import cna.apps.hangman.domain.entities.Hangman;
import cna.apps.hangman.domain.entities.MoveResult;
import cna.apps.hangman.domain.entities.WordToGuess;
import cna.apps.hangman.domain.ports.proposal.LostGame;

public class LetterProposalAssertions {

  private final static MoveResultMessageFactory MESSAGE_FACTORY = new MoveResultMessageFactory();

  public static void assertProgressingGame(ProgressingGame progressingGame, String mask, int hangmanStep, MoveResult moveResult, char proposedLetter) {
    assertEquals(mask, progressingGame.getMask());
    assertEquals(hangmanStep, progressingGame.getHangmanStep());
    assertEquals(MESSAGE_FACTORY.getMessage(moveResult, proposedLetter, null), progressingGame.getMessage());
  }

  public static void assertLostGame(LostGame lostGame, String expectedMask, int expectedHangmanStep, WordToGuess expectedWordToGuess, char proposedLetter) {
    assertEquals(expectedMask, lostGame.getMask());
    assertEquals(expectedHangmanStep, lostGame.getHangmanStep());
    assertEquals(expectedWordToGuess.getValue(), lostGame.getWordToGuess());
    assertEquals(MESSAGE_FACTORY.getMessage(LOST_GAME, proposedLetter, expectedWordToGuess), lostGame.getMessage());
  }

  public static void assertWonGame(WonGame wonGame, WordToGuess wordToGuess) {
    assertEquals(wordToGuess.getValue(), wonGame.getMask());
    assertFalse(new Hangman(wonGame.getHangmanStep()).isFull());
    assertEquals(MESSAGE_FACTORY.getMessage(WON_GAME), wonGame.getMessage());
  }
  
}
