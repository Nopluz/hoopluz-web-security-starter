package hoopluz.security.common;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;
import java.util.Optional;

public class Securities {

  public static Optional<JwtUser> getCurrent() {
    Authentication authentication = SecurityContextHolder
      .getContext()
      .getAuthentication();

    if (Objects.isNull(authentication)) {
      return Optional.empty();
    }

    if (!(authentication.getPrincipal() instanceof JwtUser)) {
      return Optional.empty();
    }
    return Optional.ofNullable((JwtUser) authentication.getPrincipal());
  }

  public static Integer getUserId() {
    return getCurrent().map(JwtUser::getId).orElse(null);
  }

}
