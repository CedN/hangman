/*
LetterProposedPresenter.java
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
package cna.apps.hangman.adapters.proposal;

import static cna.apps.hangman.adapters.proposal.Errors.GAME_OVER;
import static cna.apps.hangman.adapters.proposal.Errors.MessageKey.LOST_MESSAGE_KEY;
import static cna.apps.hangman.adapters.proposal.Errors.MessageKey.WON_MESSAGE_KEY;
import static cna.apps.hangman.api.ProposalResponse.GameStateEnum.INPROGRESS;
import static cna.apps.hangman.api.ProposalResponse.GameStateEnum.LOOSE;
import static cna.apps.hangman.api.ProposalResponse.GameStateEnum.WON;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cna.apps.hangman.api.ProposalResponse;
import cna.apps.hangman.domain.ports.proposal.GameOver;
import cna.apps.hangman.domain.ports.proposal.LetterProposalOutputBoundary;
import cna.apps.hangman.domain.ports.proposal.LostGame;
import cna.apps.hangman.domain.ports.proposal.ProgressingGame;
import cna.apps.hangman.domain.ports.proposal.WonGame;
import cna.apps.hangman.tech.BadRequestException;

public class LetterProposedPresenter implements LetterProposalOutputBoundary {

  private static final Logger LOGGER = LoggerFactory.getLogger(LetterProposedPresenter.class);

  private ProposalResponse proposalResponse;

  @Override
  public void gameInProgress(ProgressingGame letterProposalResult) {
    proposalResponse = new ProposalResponse();
    proposalResponse.gameState(INPROGRESS);
    proposalResponse.hangmanStep(letterProposalResult.getHangmanStep());
    proposalResponse.maskedWord(letterProposalResult.getMask());  
    proposalResponse.message(letterProposalResult.getMessage());
    LOGGER.info("The game is progressing with mask = '{}' and message = '{}'", proposalResponse.getMaskedWord(), proposalResponse.getMessage());
  }

  @Override
  public void lostGame(LostGame lostGame) {
    proposalResponse = new ProposalResponse();
    proposalResponse.gameState(LOOSE);
    proposalResponse.hangmanStep(lostGame.hangmanStep());
    proposalResponse.maskedWord(lostGame.mask());
    proposalResponse.setMessage(lostGame.message());
    LOGGER.info("The game is lost with mask = '{}' and message = '{}'", proposalResponse.getMaskedWord(), proposalResponse.getMessage());
  }

  @Override
  public void wonGame(WonGame wonGame) {
    proposalResponse = new ProposalResponse();
    proposalResponse.gameState(WON);
    proposalResponse.hangmanStep(wonGame.getHangmanStep());
    proposalResponse.maskedWord(wonGame.getMask());
    proposalResponse.setMessage(wonGame.getMessage());
    LOGGER.info("The game is won with mask = '{}' and message = '{}'", proposalResponse.getMaskedWord(), proposalResponse.getMessage());
  }

  @Override
  public void gameIsOver(GameOver gameOver) {
    String errorMessage = selectErrorMessage(gameOver.isWonGame());
    throw new BadRequestException(GAME_OVER.code(), errorMessage);
  }

  private String selectErrorMessage(boolean wonGame) {
    return wonGame ? GAME_OVER.message(WON_MESSAGE_KEY) : GAME_OVER.message(LOST_MESSAGE_KEY);
  }

  public ProposalResponse getProposalResponse() {
    return proposalResponse;
  }

}
