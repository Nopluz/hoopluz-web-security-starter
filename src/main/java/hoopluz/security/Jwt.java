package hoopluz.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import hoopluz.security.common.JwtToken;
import hoopluz.security.common.JwtUser;

import java.time.Instant;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Jwt {

  private final Long expires;

  private final Algorithm algorithm;

  private final ObjectMapper objectMapper;

  public Jwt(JwtProperties properties, ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
    this.algorithm = Algorithm.HMAC256(properties.getSignKey());
    this.expires = properties.getExpires();
  }

  public JwtToken encode(JwtUser user) {
    JwtToken jwtToken = new JwtToken();

    Instant now = Instant.now();
    Instant expiresAt = now.plusMillis(expires);

    if (Objects.isNull(user)) {
      return null;
    }

    JWTCreator.Builder build = JWT.create()
      .withIssuedAt(now)
      .withExpiresAt(expiresAt);

    if (Objects.nonNull(user.buildEncode())) {
      user.buildEncode().encode(build);
    }

    String token = build.sign(this.algorithm);
    jwtToken.setToken(token);
    jwtToken.setIssuedAt(now);
    jwtToken.setExpiresAt(expiresAt);
    return jwtToken;
  }

  public <T extends JwtUser> T decode(String token, Class<T> clazz) {
    JWTVerifier verifier = JWT.require(algorithm).build();
    DecodedJWT decode = verifier.verify(token);
    Map<String, String> collect = decode.getClaims().entrySet()
      .stream()
      .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().asString()));
    return this.objectMapper.convertValue(collect, clazz);
  }


}
