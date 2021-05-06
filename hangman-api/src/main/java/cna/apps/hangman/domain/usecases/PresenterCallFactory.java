package cna.apps.hangman.domain.usecases;

import cna.apps.hangman.domain.entities.Hangman;
import cna.apps.hangman.domain.entities.HangmanGame;
import cna.apps.hangman.domain.ports.proposal.LetterProposalOutputBoundary;
import cna.apps.hangman.domain.ports.proposal.LostGame;
import cna.apps.hangman.domain.ports.proposal.ProgressingGame;
import cna.apps.hangman.domain.ports.proposal.WonGame;

public class PresenterCallFactory {

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
