package hoopluz.security.common;

import hoopluz.security.JwtProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.Instant;


@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class JwtToken
  implements Serializable {

  private int id;

  private String token;

  private Instant issuedAt;

  private Instant expiresAt;

  private Instant invalidAt;

  public static JwtToken newInstance(JwtProperties jwtProperties) {
    Instant now = Instant.now();
    Instant expiresAt = now.plusMillis(jwtProperties.getExpires());
    Instant invalidAt = now.plusMillis(jwtProperties.getInvalid());
    return JwtToken.builder()
      .issuedAt(now)
      .expiresAt(expiresAt)
      .invalidAt(invalidAt)
      .build();
  }

  public JwtToken id(int id) {
    this.id = id;
    return this;
  }

  public JwtToken token(String token) {
    this.token = token;
    return this;
  }

}

