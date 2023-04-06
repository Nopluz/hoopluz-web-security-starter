package hoopluz.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import hoopluz.common.domain.JwtToken;

public class Jwt {

  private final JwtProperties properties;

  private final JWTVerifier verifier;

  private final Algorithm algorithm;

  public Jwt(JwtProperties properties) {
    this.algorithm = Algorithm.HMAC256(properties.getSignKey());
    this.properties = properties;
    verifier = JWT.require(algorithm).build();
  }

  public JwtToken encode(int id) {
    JwtToken jwtToken = JwtToken.newInstance(
      this.properties.getExpires(),
      this.properties.getInvalid()
    );

    String token = JWT.create()
      .withClaim("i", id)
      .withClaim("I", jwtToken.getIssuedAt())
      .withClaim("e", jwtToken.getExpiresAt())
      .withIssuedAt(jwtToken.getIssuedAt())
      .withExpiresAt(jwtToken.getInvalidAt())
      .sign(this.algorithm);
    return jwtToken.id(id).token(token);
  }

  public JwtToken decode(String token) {
    DecodedJWT decode = this.verifier.verify(token);
    return JwtToken.builder()
      .invalidAt(decode.getExpiresAtAsInstant())
      .expiresAt(decode.getClaim("e").asInstant())
      .issuedAt(decode.getClaim("I").asInstant())
      .id(decode.getClaim("i").asInt())
      .token(token)
      .build();
  }

}
