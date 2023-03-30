package hoopluz.security.exception.mapper;


import hoopluz.security.common.ResponseEntity;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class AbstractExceptionMapper<E extends Throwable>
  implements ExceptionMapper<E> {

  @Override
  public Response toResponse(E exception) {
    ResponseEntity entity = ResponseEntity
      .builder()
      .code(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode())
      .message(exception.getMessage())
      .build();

    return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
      .type(MediaType.APPLICATION_JSON_TYPE)
      .entity(entity)
      .build();
  }

}
