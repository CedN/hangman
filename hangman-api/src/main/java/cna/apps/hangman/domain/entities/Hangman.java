/*
Hangman.java
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

import static cna.apps.hangman.domain.entities.MoveResult.LOST_GAME;
import static cna.apps.hangman.domain.entities.MoveResult.WRONG;

public class Hangman {

  private static final int INITIAL_STEP = 0;
  
  private static final int LAST_STEP = 7;

  private int step = INITIAL_STEP;

  public Hangman() {
    this(INITIAL_STEP);
  }

  public Hangman(int step) {
    this.step = step; 
  }

  public int getStep() {
    return step;
  }

  public MoveResult increaseStep() {
    step++;
    if (isFull()) {
      return LOST_GAME;
    }
    return WRONG;
  }

  public boolean isFull() {
    return step == LAST_STEP;
  }

}
