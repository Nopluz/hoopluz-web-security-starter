package hoopluz.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationJsonPoint
  implements AuthenticationEntryPoint {

  private final ObjectMapper objectMapper;

  public AuthenticationJsonPoint(
    final ObjectMapper objectMapper
  ) {
    this.objectMapper = objectMapper;
  }

  @Override
  public void commence(
    HttpServletRequest request,
    HttpServletResponse response,
    AuthenticationException authException
  ) {
    this.onException(authException, response);
  }

  private void onException(Exception exception, HttpServletResponse response) {
    AuthenticationResponse entity = AuthenticationResponse
      .builder()
      .message(exception.getMessage())
      .code(403)
      .build();

    try {
      response.setContentType("application/json");
      response.getWriter().write(convertObjectToJson(entity));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public String convertObjectToJson(Object object)
    throws JsonProcessingException {
    if (object == null) {
      return null;
    }
    return this.objectMapper.writeValueAsString(object);
  }

}
