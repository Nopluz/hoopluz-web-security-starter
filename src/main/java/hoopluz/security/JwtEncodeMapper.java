package hoopluz.security;

import com.auth0.jwt.JWTCreator;

import java.util.HashMap;

public class JwtEncodeMapper {

  private final HashMap<String, Object> mapper;

  private JwtEncodeMapper() {
    mapper = new HashMap<>();
  }

  public static JwtEncodeMapper newInstance() {
    return new JwtEncodeMapper();
  }

  public JwtEncodeMapper put(String key, Object value) {
    mapper.put(key, value);
    return this;
  }

  protected void encode(JWTCreator.Builder builder) {
    mapper.forEach((key, value) -> builder.withClaim(key, String.valueOf(value)));
  }

}
