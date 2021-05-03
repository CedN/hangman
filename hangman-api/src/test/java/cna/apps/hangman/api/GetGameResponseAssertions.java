package cna.apps.hangman.api;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class GetGameResponseAssertions {

  public static void assertGetGameResponse(GetGameResponse getGameResponse) {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();
    Set<ConstraintViolation<GetGameResponse>> violations = validator.validate(getGameResponse);
    if (!violations.isEmpty()) {
      fail(violations.stream().map(buildViolationMessage()).collect(Collectors.joining(System.getProperty("line.separator"))));
    }
  }

  private static Function<? super ConstraintViolation<GetGameResponse>, ? extends String> buildViolationMessage() {
    return violation -> violation.getPropertyPath() + ": " + violation.getMessage();
  }
  
}
