package hoopluz.security.exception.mapper;

import org.springframework.stereotype.Component;

import javax.ws.rs.ext.Provider;

@Provider
@Component
public class GlobalExceptionMapper extends
  AbstractExceptionMapper<RuntimeException> {


}
