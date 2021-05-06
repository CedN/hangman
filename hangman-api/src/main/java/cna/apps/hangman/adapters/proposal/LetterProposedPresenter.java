package cna.apps.hangman.adapters.proposal;

import static cna.apps.hangman.api.ProposalResponse.GameStateEnum.INPROGRESS;
import static cna.apps.hangman.api.ProposalResponse.GameStateEnum.LOOSE;
import static cna.apps.hangman.api.ProposalResponse.GameStateEnum.WON;

import cna.apps.hangman.api.ProposalResponse;
import cna.apps.hangman.domain.ports.proposal.GameOver;
import cna.apps.hangman.domain.ports.proposal.LetterProposalOutputBoundary;
import cna.apps.hangman.domain.ports.proposal.LostGame;
import cna.apps.hangman.domain.ports.proposal.ProgressingGame;
import cna.apps.hangman.domain.ports.proposal.WonGame;

public class LetterProposedPresenter implements LetterProposalOutputBoundary {

  private ProposalResponse proposalResponse;

  @Override
  public void gameInProgress(ProgressingGame letterProposalResult) {
    proposalResponse = new ProposalResponse();
    proposalResponse.gameState(INPROGRESS);
    proposalResponse.hangmanStep(letterProposalResult.getHangmanStep());
    proposalResponse.maskedWord(letterProposalResult.getMask());  
    proposalResponse.message(letterProposalResult.getMessage());
  }

  @Override
  public void lostGame(LostGame lostGame) {
    proposalResponse = new ProposalResponse();
    proposalResponse.gameState(LOOSE);
    proposalResponse.hangmanStep(lostGame.getHangmanStep());
    proposalResponse.maskedWord(lostGame.getMask());
    proposalResponse.setMessage(lostGame.getMessage());
  }

  @Override
  public void wonGame(WonGame wonGame) {
    proposalResponse = new ProposalResponse();
    proposalResponse.gameState(WON);
    proposalResponse.hangmanStep(wonGame.getHangmanStep());
    proposalResponse.maskedWord(wonGame.getMask());
    proposalResponse.setMessage(wonGame.getMessage());
  }

  @Override
  public void gameIsOver(GameOver gameOver) {
    // TODO Auto-generated method stub
    
  }

  public ProposalResponse getProposalResponse() {
    return proposalResponse;
  }

}
