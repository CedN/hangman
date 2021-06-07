/*
FakeDefaultApi.java
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
package cna.apps.hangman.domain.adapters;

import java.util.Arrays;
import java.util.List;

import cna.apps.hangman.apiclients.ApiException;
import cna.apps.hangman.apiclients.randomwords.DefaultApi;
import cna.apps.hangman.apiclients.randomwords.model.RandomWord;

public class FakeDefaultApi extends DefaultApi {

  private String word;

  public void setProvidedWord(String word) {
    this.word = word;
  }

  @Override
  public List<RandomWord> fetchWord() throws ApiException {
    if (word != null) {
      return createRandomWord();
    }
    throw new ApiException("No word available!");
  }

  private List<RandomWord> createRandomWord() {
    RandomWord randomWord = new RandomWord();
    randomWord.setWord(word);
    randomWord.setDefinition("definition");
    randomWord.setPronunciation("pronunciation");
    return Arrays.asList(randomWord);
  }

}
