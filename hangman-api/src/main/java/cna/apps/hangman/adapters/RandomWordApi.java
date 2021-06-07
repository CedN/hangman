/*
RandomWordApi.java
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

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cna.apps.hangman.apiclients.ApiException;
import cna.apps.hangman.apiclients.randomwords.DefaultApi;
import cna.apps.hangman.apiclients.randomwords.model.RandomWord;
import cna.apps.hangman.domain.ports.WordProvider;

public class RandomWordApi implements WordProvider {

  private static final Logger LOGGER = LoggerFactory.getLogger(RandomWordApi.class);

  private final DefaultApi defaultApi;

  public RandomWordApi(DefaultApi defaultApi) {
    this.defaultApi = defaultApi;
  }

  @Override
  public String provide() {
    try {
      List<RandomWord> randomWordResponse = defaultApi.fetchWord();
      String word = randomWordResponse.get(0).getWord();
      LOGGER.debug("Provided word = '{}'", word);
      return word;
    } catch (ApiException e) {
      throw new RuntimeException("Random word API unavailable!", e);
    }
  }
  
}
