package hoopluz.security.exception.mapper;

import hoopluz.security.exception.UserNotFoundException;
import hoopluz.security.exception.mapper.AbstractExceptionMapper;
import org.springframework.stereotype.Component;

import javax.ws.rs.ext.Provider;

@Provider
@Component
public class ResponseExceptionMapper extends
  AbstractExceptionMapper<UserNotFoundException> {

}
