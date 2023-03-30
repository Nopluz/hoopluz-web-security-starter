package hoopluz.security.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtToken
  implements Serializable {

  private String token;

  private Instant issuedAt;

  private Instant expiresAt;

  private Instant invalidData;

}

