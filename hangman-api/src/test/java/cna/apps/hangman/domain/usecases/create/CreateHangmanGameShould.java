package cna.apps.hangman.domain.usecases.create;

import static cna.apps.hangman.domain.usecases.WordFixtures.GAME;
import static cna.apps.hangman.domain.usecases.WordFixtures.GAME_MASK;
import static cna.apps.hangman.domain.usecases.WordFixtures.HANGMAN;
import static cna.apps.hangman.domain.usecases.WordFixtures.HANGMAN_MASK;
import static cna.apps.hangman.domain.usecases.create.NewGameAssertions.assertDifferentNewGames;
import static cna.apps.hangman.domain.usecases.create.NewGameAssertions.assertNewGame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cna.apps.hangman.domain.ports.create.CreateHangmanGameInputBoundary;
import cna.apps.hangman.domain.ports.create.NewGame;
import cna.apps.hangman.domain.usecases.CreateHangmanGame;
import cna.apps.hangman.provider.WordList;

public class CreateHangmanGameShould {
  
  private NewGamePresenterSpy presenter;
  private CreateHangmanGameInputBoundary hangmanGame;
  private WordList wordList;

  @BeforeEach
  void setUp() {
    wordList = new WordList(HANGMAN, GAME);
    presenter = new NewGamePresenterSpy();
    hangmanGame = new CreateHangmanGame(wordList, presenter);
  }

  @Test
  void start_new_game() {
    hangmanGame.startNewGame();
    assertNewGame(presenter.getNewGame(), HANGMAN_MASK);
  }

  @Test
  void start_different_new_game() {
    var newGame = startNewGame();
    var otherNewGame = startNewGame();
    assertDifferentNewGames(newGame, HANGMAN_MASK, otherNewGame, GAME_MASK);
  }

  private NewGame startNewGame() {
    hangmanGame.startNewGame();    
    return presenter.getNewGame();
  }

}
