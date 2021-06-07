/*
HangmanGame.java
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
package cna.apps.hangman.domain.entities;

import static cna.apps.hangman.domain.entities.MoveResult.GOOD;
import static cna.apps.hangman.domain.entities.MoveResult.WON_GAME;

public class HangmanGame {

  private final WordToGuess wordToGuess;
  private final Mask mask;
  private final Hangman hangman;

  public HangmanGame(String wordToGuess) {
    this.wordToGuess = new WordToGuess(wordToGuess);
    mask = new Mask();
    hangman = new Hangman();
  }

  public String getMask() {
    return mask.showMaskedWord(wordToGuess);
  }

  public MoveResult tryLetter(char c) throws GameOverException {
    assertGameNotOver();
    if (wordToGuess.contains(c) && !mask.isLetterAlreadyFound(c)) {
      return processLetterFound(c);
    }
    return hangman.increaseStep();
  }

  private void assertGameNotOver() throws GameOverException {
    if (isGameWon() || isGameLost()) {
      throw new GameOverException(isGameWon());
    }
  }

  private MoveResult processLetterFound(char c) {
    mask.addLetterFound(c);
    if (isGameWon()) {
      return WON_GAME;
    }
    return GOOD;
  }

  private boolean isGameWon() {
    return mask.isDiscovered(wordToGuess);
  }

  private boolean isGameLost() {
    return hangman.isFull();
  }

  public Hangman getHangMan() {
    return hangman;
  }

  public WordToGuess getWordToGuess() {
    return wordToGuess;
  }

}
