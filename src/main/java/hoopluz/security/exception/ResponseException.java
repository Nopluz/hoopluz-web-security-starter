package hoopluz.security.exception;


public class ResponseException extends RuntimeException {

  int code;

  public int getCode() {
    return code;
  }

}
