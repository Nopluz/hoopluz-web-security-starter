package hoopluz.security.exception.mapper;


import hoopluz.security.common.ResponseEntity;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class AbstractExceptionMapper<E extends Throwable>
  implements ExceptionMapper<E> {

  @Override
  public Response toResponse(E exception) {
    ResponseEntity entity = ResponseEntity.fromException(exception);
    return Response.status(entity.getCode())
      .type(MediaType.APPLICATION_JSON_TYPE)
      .entity(entity)
      .build();
  }

}
