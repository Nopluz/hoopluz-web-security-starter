package hoopluz.security.exception;

import org.springframework.http.HttpStatus;

public class PasswordNotMatchException extends ResponseException {

  @Override
  public int getCode() {
    return HttpStatus.UNAUTHORIZED.value();
  }

  @Override
  public String getMessage() {
    return "密码匹配";
  }

}
