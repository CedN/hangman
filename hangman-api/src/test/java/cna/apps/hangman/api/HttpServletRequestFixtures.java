package cna.apps.hangman.api;

import org.springframework.http.HttpMethod;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class HttpServletRequestFixtures {
  
  static MockHttpServletRequest givenHttpServletRequest(HttpMethod httpMethod, String uri) {
    var mockedRequest = new MockHttpServletRequest(httpMethod.name(), uri);
    RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(mockedRequest));
    return mockedRequest;
  }

}
