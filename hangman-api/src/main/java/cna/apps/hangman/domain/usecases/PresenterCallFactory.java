/*
PresenterCallFactory.java
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
package cna.apps.hangman.domain.usecases;

import cna.apps.hangman.domain.entities.Hangman;
import cna.apps.hangman.domain.entities.HangmanGame;
import cna.apps.hangman.domain.ports.proposal.LetterProposalOutputBoundary;
import cna.apps.hangman.domain.ports.proposal.LostGame;
import cna.apps.hangman.domain.ports.proposal.ProgressingGame;
import cna.apps.hangman.domain.ports.proposal.WonGame;

public class PresenterCallFactory {

  private PresenterCallFactory() {}

  static PresenterCall getGameInProgressPresenterCall() {
    return new PresenterCall() {
      @Override
      public void apply(LetterProposalOutputBoundary presenter, HangmanGame hangmanGame, String message) {
        Hangman hangman = hangmanGame.getHangMan();
        ProgressingGame letterProposalResult = new ProgressingGame(message, hangmanGame.getMask(), hangman.getStep());
        presenter.gameInProgress(letterProposalResult);
      }
    };
  }

  static PresenterCall getLostGamePresenterCall() {
    return new PresenterCall() {
      @Override
      public void apply(LetterProposalOutputBoundary presenter, HangmanGame hangmanGame, String message) {
        Hangman hangman = hangmanGame.getHangMan();
        LostGame lostGame = new LostGame(message, hangmanGame.getWordToGuess(), hangman.getStep(),
            hangmanGame.getMask());
        presenter.lostGame(lostGame);
      }
    };
  }

  static PresenterCall getWonGamePresenterCall() {
    return new PresenterCall() {
      @Override
      public void apply(LetterProposalOutputBoundary presenter, HangmanGame hangmanGame, String message) {
        Hangman hangman = hangmanGame.getHangMan();
        WonGame wonGame = new WonGame(message, hangmanGame.getMask(), hangman.getStep());
        presenter.wonGame(wonGame);
      }
    };
  }

}
