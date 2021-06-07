/*
GameResource.java
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

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import cna.apps.hangman.adapters.creation.HangmanGameCreatedPresenter;
import cna.apps.hangman.adapters.creation.HangmanGameCreationController;
import cna.apps.hangman.adapters.proposal.LetterProposalController;
import cna.apps.hangman.adapters.proposal.LetterProposedPresenter;
import cna.apps.hangman.api.GameApi;
import cna.apps.hangman.api.NewGameResponse;
import cna.apps.hangman.api.ProposalResponse;

@RestController
public class GameResource implements GameApi {

  private final HangmanGameCreationController hangmanGameCreationController;
  private final HangmanGameCreatedPresenter hangmanGameCreatedPresenter;
  private final LetterProposedPresenter letterProposedPresenter;
  private final LetterProposalController letterProposalController;

  public GameResource(HangmanGameCreationController hangmanGameCreationController, HangmanGameCreatedPresenter hangmanGameCreatedPresenter, LetterProposalController letterProposalController, LetterProposedPresenter letterProposedPresenter) {
    this.hangmanGameCreationController = hangmanGameCreationController;
    this.hangmanGameCreatedPresenter = hangmanGameCreatedPresenter;
    this.letterProposalController = letterProposalController;
    this.letterProposedPresenter = letterProposedPresenter;
  }

  @Override
  public ResponseEntity<NewGameResponse> createGame() {
    hangmanGameCreationController.createNewGame();
    return ResponseEntity.ok(hangmanGameCreatedPresenter.getNewGameResponse());
  }

  @Override
  public ResponseEntity<ProposalResponse> proposeLetter(String gameId, String letter) {
    letterProposalController.proposeLetter(gameId, letter);
    return ResponseEntity.ok(letterProposedPresenter.getProposalResponse());
  }
  
}
