package hoopluz.security.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException
  extends ResponseException {

  @Override
  public String getMessage() {
    return "用户不存在";
  }

  @Override
  public int getCode() {
    return HttpStatus.NOT_FOUND.value();
  }

}
