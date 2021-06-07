/*
LetterProposalAssertions.java
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
    assertEquals(expectedMask, lostGame.mask());
    assertEquals(expectedHangmanStep, lostGame.hangmanStep());
    assertEquals(expectedWordToGuess, lostGame.wordToGuess());
    assertEquals(MESSAGE_FACTORY.getMessage(LOST_GAME, proposedLetter, expectedWordToGuess), lostGame.message());
  }

  public static void assertWonGame(WonGame wonGame, WordToGuess wordToGuess) {
    assertEquals(wordToGuess.value(), wonGame.getMask());
    assertFalse(new Hangman(wonGame.getHangmanStep()).isFull());
    assertEquals(MESSAGE_FACTORY.getMessage(WON_GAME), wonGame.getMessage());
  }
  
}
