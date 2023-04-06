package hoopluz.security;

import hoopluz.common.domain.JwtToken;
import hoopluz.common.domain.JwtUser;
import hoopluz.common.domain.exception.UnauthorizedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;
import java.util.Optional;

public class Securities {

  public static Optional<JwtToken> getCurrent() {
    Authentication authentication = SecurityContextHolder
      .getContext()
      .getAuthentication();

    if (Objects.isNull(authentication)) {
      return Optional.empty();
    }

    if (!(authentication.getPrincipal() instanceof JwtUser)) {
      return Optional.empty();
    }
    return Optional.ofNullable((JwtToken) authentication.getPrincipal());
  }

  public static Integer getUserId() {
    return getCurrent()
      .map(JwtToken::getId)
      .orElseThrow(UnauthorizedException::new);
  }

}
