package cna.apps.hangman.domain.usecases;

import java.util.UUID;

import cna.apps.hangman.domain.entities.GameOverException;
import cna.apps.hangman.domain.entities.HangmanGame;
import cna.apps.hangman.domain.entities.MoveResult;
import cna.apps.hangman.domain.ports.GameRepository;
import cna.apps.hangman.domain.ports.proposal.GameOver;
import cna.apps.hangman.domain.ports.proposal.LetterProposalOutputBoundary;
import cna.apps.hangman.domain.ports.proposal.ProposeLetterInputBoundary;

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
      presenter.gameIsOver(new GameOver(e.isWonGame()));
    }
  }

  private void tryLetter(HangmanGame hangmanGame, char letter) throws GameOverException {
    MoveResult moveResult = hangmanGame.tryLetter(letter);
    new MoveResultProcessor(hangmanGame, presenter).process(moveResult, letter);
  }

}
