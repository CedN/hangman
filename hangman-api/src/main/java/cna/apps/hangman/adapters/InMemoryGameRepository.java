/*
InMemoryGameRepository.java
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
package cna.apps.hangman.adapters;

import java.util.HashMap;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cna.apps.hangman.domain.entities.HangmanGame;
import cna.apps.hangman.domain.ports.GameRepository;

public class InMemoryGameRepository implements GameRepository {

  private static final Logger LOGGER = LoggerFactory.getLogger(InMemoryGameRepository.class);

  private HashMap<UUID, HangmanGame> games = new HashMap<>();

  public void add(UUID gameId, HangmanGame hangmanGame) {
    games.put(gameId, hangmanGame);
    LOGGER.debug("The hangman game '{}' has been added with the ID = '{}'",
        hangmanGame.getWordToGuess().value(), gameId);
  }

  @Override
  public HangmanGame get(UUID gameId) {
    HangmanGame hangmanGame = games.get(gameId);
    LOGGER.debug("The ID = '{}' matches the hangman game '{}'", gameId, hangmanGame);
    return hangmanGame;
  }

}
