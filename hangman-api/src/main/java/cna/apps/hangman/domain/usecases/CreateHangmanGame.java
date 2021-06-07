/*
CreateHangmanGame.java
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

import cna.apps.hangman.domain.entities.HangmanGame;
import cna.apps.hangman.domain.ports.GameRepository;
import cna.apps.hangman.domain.ports.WordProvider;
import cna.apps.hangman.domain.ports.create.CreateHangmanGameInputBoundary;
import cna.apps.hangman.domain.ports.create.NewGame;
import cna.apps.hangman.domain.ports.create.NewGameOutputBoundary;

public class CreateHangmanGame implements CreateHangmanGameInputBoundary {

  private final WordProvider wordProvider;
  private final NewGameOutputBoundary presenter;
  private final GameRepository gameRepository;

  public CreateHangmanGame(WordProvider wordProvider, GameRepository gameRepository, NewGameOutputBoundary presenter) {
    this.wordProvider = wordProvider;
    this.gameRepository = gameRepository;
    this.presenter = presenter;
  }

  @Override
  public void startNewGame() {
    var hangmanGame = new HangmanGame(wordProvider.provide());
    UUID gameId = UUID.randomUUID();
    gameRepository.add(gameId, hangmanGame);
    presenter.newGameCreated(new NewGame(gameId, hangmanGame.getMask()));
  }

}
