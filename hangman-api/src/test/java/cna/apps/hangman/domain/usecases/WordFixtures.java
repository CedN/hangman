/*
WordFixtures.java
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

public class WordFixtures {
  
  public final static String HANGMAN = "hangman";

  public final static String HANGMAN_MASK = maskWord(HANGMAN);

  public final static String HANGMAN_MASK_WITH_H = HANGMAN.replaceAll("[^h]", "-");

  public final static String HANGMAN_MASK_WITH_A = HANGMAN.replaceAll("[^a]", "-");

  public final static String GAME = "game";

  public final static String GAME_MASK = maskWord(GAME);

  private static String maskWord(String hangman) {
    return hangman.replaceAll("[a-z]", "-");
  }

}
