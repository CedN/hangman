/*
MoveResultProcessor.java
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

import static cna.apps.hangman.domain.entities.MoveResult.GOOD;
import static cna.apps.hangman.domain.entities.MoveResult.LOST_GAME;
import static cna.apps.hangman.domain.entities.MoveResult.WON_GAME;
import static cna.apps.hangman.domain.entities.MoveResult.WRONG;

import java.util.Map;

import cna.apps.hangman.domain.entities.HangmanGame;
import cna.apps.hangman.domain.entities.MoveResult;
import cna.apps.hangman.domain.ports.proposal.LetterProposalOutputBoundary;

public class MoveResultProcessor {

  private static Map<MoveResult, PresenterCall> PRESENTER_CALLS = Map.of(
    GOOD, PresenterCallFactory.getGameInProgressPresenterCall(), 
    WRONG, PresenterCallFactory.getGameInProgressPresenterCall(), 
    LOST_GAME, PresenterCallFactory.getLostGamePresenterCall(),
    WON_GAME, PresenterCallFactory.getWonGamePresenterCall());

  private HangmanGame hangmanGame;
  private MoveResultMessageFactory messageFactory;
  private LetterProposalOutputBoundary presenter;

  MoveResultProcessor(HangmanGame hangmanGame, LetterProposalOutputBoundary presenter) {
    this.hangmanGame = hangmanGame;
    this.presenter = presenter;
    messageFactory = new MoveResultMessageFactory();
  }

  void process(MoveResult moveResult, char letter) {
    String message = messageFactory.getMessage(moveResult, letter, hangmanGame.getWordToGuess());
    PRESENTER_CALLS.get(moveResult).apply(presenter, hangmanGame, message);
  }

}
