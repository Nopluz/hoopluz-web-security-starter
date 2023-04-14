package hoopluz.security;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class JwtFilter extends OncePerRequestFilter {

  private final Jwt jwt;

  public JwtFilter(Jwt jwt) {
    this.jwt = jwt;
  }

  @Override
  protected void doFilterInternal(
    HttpServletRequest request,
    HttpServletResponse response,
    FilterChain chain
  ) {
    try {
      String token = this.getToken(request);
      JwtToken jwtToken = jwt.decode(token);
      UsernamePasswordAuthenticationToken authentication =
        new UsernamePasswordAuthenticationToken(
          jwtToken,
          token,
          AuthorityUtils.NO_AUTHORITIES
        );
      authentication.setDetails(new WebAuthenticationDetails(request));
      SecurityContextHolder.getContext().setAuthentication(authentication);
      chain.doFilter(request, response);
    } catch (Exception exception) {
      throw new BadCredentialsException(exception.getMessage());
    }
  }

  private String getToken(HttpServletRequest request) {
    String token = request.getHeader("Authorization");
    if (Objects.nonNull(token)) {
      return token;
    }
    return request.getParameter("Authorization");
  }

}
