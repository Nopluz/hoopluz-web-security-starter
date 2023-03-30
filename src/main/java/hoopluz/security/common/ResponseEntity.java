package hoopluz.security.common;

import hoopluz.security.exception.ResponseException;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@SuperBuilder
public class ResponseEntity implements Serializable {

  private Integer code;

  private String message;

  private Object data;

  public static ResponseEntity fromException(Throwable exception) {
    ResponseEntityBuilder<?, ?> response = ResponseEntity
      .builder()
      .message(exception.getMessage())
      .code(500);
    if (exception instanceof ResponseException) {
      response.code(((ResponseException) exception).getCode());
    }
    exception.printStackTrace();
    return response.build();
  }

}