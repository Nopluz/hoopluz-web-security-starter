package hoopluz.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

  /**
   * status code: success 200 ; other is exception or error
   */
  private Integer code;

  /**
   * error message;
   */
  private String message;

}
