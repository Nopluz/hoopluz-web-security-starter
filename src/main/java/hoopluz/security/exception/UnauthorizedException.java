package hoopluz.security.exception;

import org.springframework.http.HttpStatus;

public class UnauthorizedException
  extends ResponseException {

  @Override
  public String getMessage() {
    return HttpStatus.UNAUTHORIZED.getReasonPhrase();
  }

  @Override
  public int getCode() {
    return HttpStatus.UNAUTHORIZED.value();
  }

}
