/*
FakeProposeLetter.java
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
package cna.apps.hangman.tech;

import java.util.UUID;

import cna.apps.hangman.domain.entities.WordToGuess;
import cna.apps.hangman.domain.ports.proposal.GameOver;
import cna.apps.hangman.domain.ports.proposal.LetterProposalOutputBoundary;
import cna.apps.hangman.domain.ports.proposal.LostGame;
import cna.apps.hangman.domain.ports.proposal.ProgressingGame;
import cna.apps.hangman.domain.ports.proposal.ProposeLetterInputBoundary;
import cna.apps.hangman.domain.ports.proposal.WonGame;
import cna.apps.hangman.domain.usecases.UnknownGameException;

public class FakeProposeLetter implements ProposeLetterInputBoundary {

  private final LetterProposalOutputBoundary presenter;
  private String mask;
  private int hangmanStep;
  private String message;
  private GameStatus gameStatus;
  private String wordToGuess;
  private boolean isWonGame;

  private enum GameStatus {
    IN_PROGRESS, LOST, WON, GAME_OVER, UNKNOW_GAME;
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

  public void setGameWon(String message, String mask, int hangmanStep) {
    this.gameStatus = GameStatus.WON;
    this.message = message;
    this.mask = mask;
    this.hangmanStep = hangmanStep;
  }

  public void setGameIsOver(boolean isWonGame) {
    this.gameStatus = GameStatus.GAME_OVER;
    this.isWonGame = isWonGame;
  }

  public void setUnknownGame() {
    this.gameStatus = GameStatus.UNKNOW_GAME;
  }

  @Override
  public void tryLetter(UUID gameId, char c) throws UnknownGameException {
    switch (gameStatus) {
      case IN_PROGRESS -> presenter.gameInProgress(new ProgressingGame(message, mask, hangmanStep));
      case LOST -> presenter.lostGame(new LostGame(message, new WordToGuess(wordToGuess), hangmanStep, mask));
      case WON -> presenter.wonGame(new WonGame(message, mask, hangmanStep));
      case GAME_OVER -> presenter.gameIsOver(new GameOver(isWonGame));
      case UNKNOW_GAME -> throw new UnknownGameException();
    }
  }


}
