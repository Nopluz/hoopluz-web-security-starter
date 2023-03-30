package hoopluz.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

  @Bean
  public Jwt getJwt(JwtProperties properties) {
    return new Jwt(properties);
  }

}
