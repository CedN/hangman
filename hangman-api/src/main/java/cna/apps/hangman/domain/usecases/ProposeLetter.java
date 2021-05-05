package cna.apps.hangman.domain.usecases;

import java.util.UUID;

import cna.apps.hangman.domain.entities.GameOverException;
import cna.apps.hangman.domain.entities.HangmanGame;
import cna.apps.hangman.domain.entities.MoveResult;
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
  private final MoveResultMessageFactory messageFactory;

  public ProposeLetter(GameRepository gameRepository, LetterProposalOutputBoundary presenter) {
    this.gameRepository = gameRepository;
    this.presenter = presenter;
    messageFactory = new MoveResultMessageFactory();
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
    String message = messageFactory.createMessage(moveResult, letter, hangmanGame.getWordToGuess());
    presentMoveResult(hangmanGame, message, moveResult);
  }

  private void presentMoveResult(HangmanGame hangmanGame, String message, MoveResult moveResult) {
    switch (moveResult) {
      default: // moveResult never null but the default case is necessary to unit test coverage
      case GOOD:
      case WRONG:
        presenter.gameInProgress(new ProgressingGame(message, hangmanGame.getMask(), hangmanGame.getHangMan().getStep()));
        break;
      case LOST_GAME:
        presenter.lostGame(
            new LostGame(message, hangmanGame.getWordToGuess(), hangmanGame.getHangMan().getStep(), hangmanGame.getMask()));
        break;
      case WON_GAME:
        presenter.wonGame(new WonGame(hangmanGame.getMask(), hangmanGame.getHangMan().getStep()));  
    }
  }

}
