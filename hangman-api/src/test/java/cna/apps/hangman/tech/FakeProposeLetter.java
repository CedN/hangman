package cna.apps.hangman.tech;

import java.util.UUID;

import cna.apps.hangman.domain.entities.WordToGuess;
import cna.apps.hangman.domain.ports.proposal.LetterProposalOutputBoundary;
import cna.apps.hangman.domain.ports.proposal.LostGame;
import cna.apps.hangman.domain.ports.proposal.ProgressingGame;
import cna.apps.hangman.domain.ports.proposal.ProposeLetterInputBoundary;

public class FakeProposeLetter implements ProposeLetterInputBoundary {

  private final LetterProposalOutputBoundary presenter;
  private String mask;
  private int hangmanStep;
  private String message;
  private GameStatus gameStatus;
  private String wordToGuess;

  private enum GameStatus {
    IN_PROGRESS, LOST, WON;
  }

  public FakeProposeLetter(LetterProposalOutputBoundary presenter) {
    this.presenter = presenter;
  }

  public void setGameInProgressValues(String message, String mask, int hangmanStep) {
    this.gameStatus = GameStatus.IN_PROGRESS;
    this.message = message;
    this.mask = mask;
    this.hangmanStep = hangmanStep;
  }

  public void setGameLost(String message, String wordToGuess, String mask) {
    this.gameStatus = GameStatus.LOST;
    this.message = message;
    this.wordToGuess = wordToGuess;
    this.mask = mask;
    this.hangmanStep = 7;
  }

  @Override
  public void tryLetter(UUID gameId, char c) {
    switch (gameStatus) {
      case IN_PROGRESS:
        presenter.gameInProgress(new ProgressingGame(message, mask, hangmanStep));
        break;
      case LOST:
        presenter.lostGame(new LostGame(message, new WordToGuess(wordToGuess), hangmanStep, mask));
        break;
      case WON:
        break;
    }
  }

}
