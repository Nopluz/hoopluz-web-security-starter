package hoopluz.security.exception;

import hoopluz.security.common.ResponseEntity;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Log4j2
@ResponseStatus
@RestControllerAdvice
public class ExceptionController {

  @ExceptionHandler({RuntimeException.class})
  public ResponseEntity runtimeExceptionHandler(RuntimeException e) {
    log.warn("ExceptionController try exception {}", ExceptionUtils.getStackTrace(e));
    return ResponseEntity.fromException(e);
  }

}
