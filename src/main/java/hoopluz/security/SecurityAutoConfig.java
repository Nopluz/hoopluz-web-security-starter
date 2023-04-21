package hoopluz.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;

@Configuration
@EnableWebSecurity
@EnableConfigurationProperties(JwtProperties.class)
public class SecurityAutoConfig {

  @Bean
  public Jwt getJwt(JwtProperties jwtProperties) {
    return new Jwt(jwtProperties);
  }

  @Bean
  @ConditionalOnMissingBean
  public ObjectMapper getObjectMapper() {
    return new ObjectMapper();
  }

  @Bean
  @ConditionalOnMissingBean
  public SecurityFilterChain filterChain(
    Jwt jwt,
    HttpSecurity http,
    ObjectMapper objectMapper
  ) throws Exception {
    http.cors();
    http
      .addFilterBefore(new JwtFilter(jwt), UsernamePasswordAuthenticationFilter.class)
      .authorizeRequests()
      .requestMatchers(CorsUtils::isPreFlightRequest)
      .permitAll()
      .anyRequest()
      .authenticated()
      .and()
      .csrf()
      .disable()
      .exceptionHandling()
      .authenticationEntryPoint(new AuthenticationJsonPoint(objectMapper))
      .and()
      .sessionManagement()
      .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    return http.build();
  }

  @Bean
  @ConditionalOnMissingBean
  public WebSecurityCustomizer webSecurityCustomizer() {
    return web -> web.ignoring().antMatchers(
      "/services/public/**",
      "/favicon.ico",
      "/doc/**"
    );
  }

}
