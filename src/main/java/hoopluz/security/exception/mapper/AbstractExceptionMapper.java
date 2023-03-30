package hoopluz.security.exception.mapper;


import hoopluz.security.common.ResponseEntity;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class AbstractExceptionMapper<E extends Throwable>
  implements ExceptionMapper<E> {

  private final MediaType mediaType = MediaType.APPLICATION_JSON_TYPE;

  private Response.StatusType statusType = Response.Status.INTERNAL_SERVER_ERROR;

  @Override
  public Response toResponse(E exception) {
    ResponseEntity entity = ResponseEntity
      .builder()
      .code(500)
      .message(exception.getMessage())
      .build();
    return Response.status(statusType).type(mediaType).entity(entity).build();
  }

}
