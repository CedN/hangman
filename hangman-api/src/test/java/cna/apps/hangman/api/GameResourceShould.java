package cna.apps.hangman.api;

import static cna.apps.hangman.api.HttpServletRequestFixtures.givenHttpServletRequest;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

public class GameResourceShould {

  private static final String GAME_RESOURCE_URI = "game";

  private static final String UUID_REGEX = "[0-9a-fA-F]{8}\\b-[0-9a-fA-F]{4}\\b-[0-9a-fA-F]{4}\\b-[0-9a-fA-F]{4}\\b-[0-9a-fA-F]{12}";

  private static final String LOCATION_REGEX = "http\\://localhost/game/" + UUID_REGEX;

  @Test
  void return_game_location_not_null_when_new_game_is_created() {
    givenHttpServletRequest(HttpMethod.POST, GAME_RESOURCE_URI);
    var response = new GameResource().createGame();
    assertNotNull(response.getHeaders().getLocation());
  }

  @Test
  void return_game_location_ends_with_id_when_new_game_is_created() {
    givenHttpServletRequest(HttpMethod.POST, GAME_RESOURCE_URI);
    var response = new GameResource().createGame();
    assertLocationHeaderPattern(response.getHeaders());
  }

  private void assertLocationHeaderPattern(HttpHeaders httpHeaders) {
    var location = httpHeaders.getLocation();
    var expectedLocationPattern = Pattern.compile(LOCATION_REGEX);
    Matcher matcher = expectedLocationPattern.matcher(location.toString());
    assertTrue(matcher.matches());
  }

  @Test
  void return_different_location_for_each_new_game() {
    givenHttpServletRequest(HttpMethod.POST, GAME_RESOURCE_URI);
    var gameResource = new GameResource();
    var aNewGameResponse = gameResource.createGame();
    var anotherNewGameResponse = gameResource.createGame();
    assertNotEquals(aNewGameResponse.getHeaders().getLocation(), anotherNewGameResponse.getHeaders().getLocation());
  }


  
}
