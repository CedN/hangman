package cna.apps.hangman.api;

import static cna.apps.hangman.api.HttpServletRequestFixtures.givenHttpServletRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Set;
import java.util.UUID;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

public class GameResourceShould {

  private static final String GAME_RESOURCE_URI = "game";

  private static final String UUID_REGEX = "[0-9a-fA-F]{8}\\b-[0-9a-fA-F]{4}\\b-[0-9a-fA-F]{4}\\b-[0-9a-fA-F]{4}\\b-[0-9a-fA-F]{12}";

  private static final String LOCATION_REGEX = "http\\://localhost/game/" + UUID_REGEX;

  private GameRepository gameRepository;

  private GameResource gameResourceUnderTest;

  @BeforeEach
  void setUp() {
    gameRepository = new GameRepository();
    gameResourceUnderTest = new GameResource(gameRepository);
  }

  @Nested
  class CreationGameApi {

    @Test
    void return_game_location_not_null_when_new_game_is_created() {
      givenHttpServletRequest(HttpMethod.POST, GAME_RESOURCE_URI);
      var response = gameResourceUnderTest.createGame();
      assertNotNull(response.getHeaders().getLocation());
    }

    @Test
    void return_game_location_ends_with_id_when_new_game_is_created() {
      givenHttpServletRequest(HttpMethod.POST, GAME_RESOURCE_URI);
      var response = gameResourceUnderTest.createGame();
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
      var aNewGameResponse = gameResourceUnderTest.createGame();
      var anotherNewGameResponse = gameResourceUnderTest.createGame();
      assertNotEquals(aNewGameResponse.getHeaders().getLocation(), anotherNewGameResponse.getHeaders().getLocation());
    }

  }

  @Nested
  class GameStatusApi {

    @Test
    void return_bad_request_when_get_not_existing_game() {
      String gameId = UUID.randomUUID().toString();
      givenHttpServletRequest(HttpMethod.GET, GAME_RESOURCE_URI + '/' + gameId);
      var response = gameResourceUnderTest.fetchGame(gameId);
      assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void return_game_status_from_a_new_game() {
      String gameId = givenNewGame();
      givenHttpServletRequest(HttpMethod.GET, GAME_RESOURCE_URI + '/' + gameId);
      var response = gameResourceUnderTest.fetchGame(gameId);
      assertEquals(HttpStatus.OK, response.getStatusCode());
      assertNotNull(response.getBody());
      assertGetGameResponse(response.getBody());
    }

    private String givenNewGame() {
      GameHolder gameHolder = new GameHolder();
      gameRepository.addGameHolder(gameHolder);
      return gameHolder.getGameId();
    }

    private void assertGetGameResponse(GetGameResponse getGameResponse) {
      ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
      Validator validator = factory.getValidator();
      Set<ConstraintViolation<GetGameResponse>> violations = validator.validate(getGameResponse);
      if (!violations.isEmpty()) {
        fail(violations.stream().map(violation -> violation.getMessage()).collect(Collectors.joining(" ++ ")));
      }
    }

  }
}
