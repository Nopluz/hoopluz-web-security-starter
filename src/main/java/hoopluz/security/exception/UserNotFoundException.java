package hoopluz.security.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException
  extends ResponseException {

  @Override
  public String getMessage() {
    return "object not found !";
  }

  @Override
  public int getCode() {
    return HttpStatus.NOT_FOUND.value();
  }

}
