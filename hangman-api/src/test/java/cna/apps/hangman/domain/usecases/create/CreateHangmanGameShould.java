/*
CreateHangmanGameShould.java
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
package cna.apps.hangman.domain.usecases.create;

import static cna.apps.hangman.domain.usecases.WordFixtures.GAME;
import static cna.apps.hangman.domain.usecases.WordFixtures.GAME_MASK;
import static cna.apps.hangman.domain.usecases.WordFixtures.HANGMAN;
import static cna.apps.hangman.domain.usecases.WordFixtures.HANGMAN_MASK;
import static cna.apps.hangman.domain.usecases.create.NewGameAssertions.assertDifferentNewGames;
import static cna.apps.hangman.domain.usecases.create.NewGameAssertions.assertNewGame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cna.apps.hangman.adapters.InMemoryGameRepository;
import cna.apps.hangman.adapters.WordList;
import cna.apps.hangman.domain.entities.HangmanGame;
import cna.apps.hangman.domain.ports.create.CreateHangmanGameInputBoundary;
import cna.apps.hangman.domain.ports.create.NewGame;
import cna.apps.hangman.domain.usecases.CreateHangmanGame;

public class CreateHangmanGameShould {
  
  private NewGamePresenterSpy presenter;
  private InMemoryGameRepository gameRepository;
  private CreateHangmanGameInputBoundary createHangmanGame;
  private WordList wordList;

  @BeforeEach
  void setUp() {
    wordList = new WordList(HANGMAN, GAME);
    gameRepository = new InMemoryGameRepository();
    presenter = new NewGamePresenterSpy();
    createHangmanGame = new CreateHangmanGame(wordList, gameRepository, presenter);
  }

  @Test
  void start_new_game() {
    createHangmanGame.startNewGame();
    assertNewGame(presenter.getNewGame(), HANGMAN_MASK);
    assertNewGameInGameRepository(presenter.getNewGame());
  }

  private void assertNewGameInGameRepository(NewGame newGame) {
    HangmanGame hangmanGame = gameRepository.get(newGame.id());
    assertNotNull(hangmanGame);
    assertEquals(newGame.mask(), hangmanGame.getMask());
  }

  @Test
  void start_different_new_game() {
    var newGame = startNewGame();
    var otherNewGame = startNewGame();
    assertDifferentNewGames(newGame, HANGMAN_MASK, otherNewGame, GAME_MASK);
  }

  private NewGame startNewGame() {
    createHangmanGame.startNewGame();    
    return presenter.getNewGame();
  }

}
