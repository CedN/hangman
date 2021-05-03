package cna.apps.hangman.domain.usecases;

import java.util.UUID;

import cna.apps.hangman.domain.core.GameOverException;
import cna.apps.hangman.domain.core.HangmanGame;
import cna.apps.hangman.domain.core.MoveResult;
import cna.apps.hangman.domain.ports.GameRepository;
import cna.apps.hangman.domain.ports.proposal.GameOver;
import cna.apps.hangman.domain.ports.proposal.LetterProposalOutputBoundary;
import cna.apps.hangman.domain.ports.proposal.LostGame;
import cna.apps.hangman.domain.ports.proposal.ProgressingGame;
import cna.apps.hangman.domain.ports.proposal.ProposeLetterInputBoundary;
import cna.apps.hangman.domain.ports.proposal.WonGame;

public class ProposeLetter implements ProposeLetterInputBoundary {

  private final GameRepository gameRepository;
  private final LetterProposalOutputBoundary presenter;

  public ProposeLetter(GameRepository gameRepository, LetterProposalOutputBoundary presenter) {
    this.gameRepository = gameRepository;
    this.presenter = presenter;
  }

  @Override
  public void tryLetter(UUID gameId, char letter) {
    HangmanGame hangmanGame = gameRepository.get(gameId);
    try {
      tryLetter(hangmanGame, letter);
    } catch (GameOverException e) {
      presenter.gameIsOver(new GameOver(e));
    }
  }

  private void tryLetter(HangmanGame hangmanGame, char letter) throws GameOverException {
    MoveResult moveResult = hangmanGame.tryLetter(letter);
    presentMoveResult(hangmanGame, moveResult);
  }

  private void presentMoveResult(HangmanGame hangmanGame, MoveResult moveResult) {
    switch (moveResult) {
      default: // moveResult never null but the default case is necessary to unit test coverage
      case GOOD:
      case WRONG:
        presenter.gameInProgress(new ProgressingGame(hangmanGame.getMask(), hangmanGame.getHangMan().getStep()));
        break;
      case LOST_GAME:
        presenter.lostGame(
            new LostGame(hangmanGame.getWordToGuess(), hangmanGame.getHangMan().getStep(), hangmanGame.getMask()));
        break;
      case WON_GAME:
        presenter.wonGame(new WonGame(hangmanGame.getMask(), hangmanGame.getHangMan().getStep()));  
    }
  }

}
