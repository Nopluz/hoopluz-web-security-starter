package hoopluz.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

  @Bean
  @ConditionalOnMissingBean(ObjectMapper.class)
  public ObjectMapper getObjectMapper() {
    return new ObjectMapper();
  }

  @Bean
  public Jwt getJwt(ObjectMapper objectMapper, JwtProperties properties) {
    return new Jwt(properties, objectMapper);
  }

}
