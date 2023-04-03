package hoopluz.security.common;

import hoopluz.security.exception.ResponseException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseEntity<T> implements Serializable {

  private Integer code;

  private String message;

  private T data;

  private ResponseEntity(T data) {
    this.data = data;
    this.code = 200;
    this.message = "ok";
  }

  public static ResponseEntity<Object> fromException(Throwable exception) {
    ResponseEntityBuilder<Object, ?, ?> response = ResponseEntity
      .builder()
      .message(exception.getMessage())
      .code(500);
    if (exception instanceof ResponseException) {
      response.code(((ResponseException) exception).getCode());
    }
    exception.printStackTrace();
    return response.build();
  }

  public static <R> ResponseEntity<R> ok(R data) {
    return new ResponseEntity<>(data);
  }

  public static ResponseEntity<Void> ok() {
    ResponseEntity<Void> response = new ResponseEntity<>();
    response.setCode(200);
    response.setMessage("ok");
    return response;
  }


}
