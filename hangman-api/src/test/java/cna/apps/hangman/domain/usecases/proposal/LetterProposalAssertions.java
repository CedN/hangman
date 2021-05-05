package cna.apps.hangman.domain.usecases.proposal;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cna.apps.hangman.domain.ports.proposal.ProgressingGame;
import cna.apps.hangman.domain.usecases.MoveResultMessageFactory;
import cna.apps.hangman.domain.entities.MoveResult;
import cna.apps.hangman.domain.ports.proposal.LostGame;

public class LetterProposalAssertions {

  public static void assertProgressingGame(ProgressingGame progressingGame, String mask, int hangmanStep, MoveResult moveResult, char proposedLetter) {
    assertEquals(mask, progressingGame.getMask());
    assertEquals(hangmanStep, progressingGame.getHangmanStep());
    assertEquals(new MoveResultMessageFactory().createMessage(moveResult, proposedLetter, null), progressingGame.getMessage());
  }

  public static void assertLostGame(LostGame lostGame, String expectedMask, int expectedHangmanStep, String expectedWordToGuess) {
    assertEquals(expectedMask, lostGame.getMask());
    assertEquals(expectedHangmanStep, lostGame.getHangmanStep());
    assertEquals(expectedWordToGuess, lostGame.getWordToGuess());
  }
  
}
