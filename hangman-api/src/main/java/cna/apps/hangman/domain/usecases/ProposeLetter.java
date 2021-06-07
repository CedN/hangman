/*
ProposeLetter.java
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
  public void tryLetter(UUID gameId, char letter) throws UnknownGameException {
    HangmanGame hangmanGame = getHangmanGameFromId(gameId);
    try {
      tryLetter(hangmanGame, letter);
    } catch (GameOverException e) {
      presenter.gameIsOver(new GameOver(e.isWonGame()));
    }
  }

  private HangmanGame getHangmanGameFromId(UUID gameId) throws UnknownGameException {
    HangmanGame hangmanGame = gameRepository.get(gameId);
    assertHangmanGameNotNull(hangmanGame);
    return hangmanGame;
  }

  private void assertHangmanGameNotNull(HangmanGame hangmanGame) throws UnknownGameException {
    if (hangmanGame == null) {
      throw new UnknownGameException();
    }
  }

  private void tryLetter(HangmanGame hangmanGame, char letter) throws GameOverException {
    MoveResult moveResult = hangmanGame.tryLetter(letter);
    new MoveResultProcessor(hangmanGame, presenter).process(moveResult, letter);
  }

}
