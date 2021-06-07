/*
Mask.java
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
package cna.apps.hangman.domain.entities;

class Mask {

  private static final Character MASKED_LETTER = Character.valueOf('-');
  private String letterFounds;

  Mask() {
    this.letterFounds = "";
  }

  void addLetterFound(char c) {
    letterFounds = letterFounds + c;
  }

  boolean isLetterAlreadyFound(char c) {
    return letterFounds.indexOf(c) != -1;
  }

  String showMaskedWord(WordToGuess wordToGuess) {
    return wordToGuess.value().codePoints().mapToObj(c -> (char) c).map(this::maskLetterNotFound)
        .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();
  }

  private char maskLetterNotFound(char c) {
    if (letterFounds.indexOf(c) != -1)
      return c;
    return MASKED_LETTER;
  }

  boolean isDiscovered(WordToGuess wordToGuess) {
    return showMaskedWord(wordToGuess).indexOf(MASKED_LETTER) == -1;
  }

}
