package hoopluz.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.Instant;

/**
 * jwt token rest domain
 */

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class JwtToken
  implements Serializable {

  /**
   * user id
   */
  private int id;

  /**
   * jwt token
   */
  private String token;

  /**
   * 签发时间
   */
  private Instant issuedAt;

  /**
   * 过期时间
   */
  private Instant expiresAt;

  /**
   * 失效时间
   */
  private Instant invalidAt;

  public static JwtToken newInstance(Long expires, Long invalid) {
    Instant now = Instant.now();
    Instant expiresAt = now.plusMillis(expires);
    Instant invalidAt = now.plusMillis(invalid);
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

