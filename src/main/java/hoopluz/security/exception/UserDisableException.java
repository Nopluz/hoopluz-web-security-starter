package hoopluz.security.exception;

import org.springframework.http.HttpStatus;

public class UserDisableException
  extends ResponseException {

  @Override
  public String getMessage() {
    return "用户被禁用";
  }

  @Override
  public int getCode() {
    return HttpStatus.UNAUTHORIZED.value();
  }

}
