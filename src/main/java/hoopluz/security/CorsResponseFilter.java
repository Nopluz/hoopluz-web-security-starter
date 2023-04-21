package hoopluz.security;

import org.springframework.stereotype.Component;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;

@Provider
@Component
public class CorsResponseFilter implements ContainerResponseFilter {

  @Override
  public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {
    MultivaluedMap<String, Object> headers = responseContext.getHeaders();
    headers.add("Access-Control-Allow-Credentials", "true");
    headers.add("Access-Control-Allow-Origin", requestContext.getHeaderString("Origin"));
    headers.add("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS");
    headers.add("Access-Control-Allow-Headers", "Authorization");
  }

}
