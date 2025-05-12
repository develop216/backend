package com.proxiad.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.nimbusds.jose.jwk.source.ImmutableSecret;
import com.proxiad.beans.Utilisateur;
import com.proxiad.service.UtilisateurDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig{
	
	
	private UtilisateurDetailsService utilisateurDetailsService;
	private JwtUtils jwtUtils;
	
	@Value("${security.jwt.secret-key}")
    private String secretKey;
    
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
				.sessionManagement(sm->sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(csrf->csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(auth ->
                		auth.requestMatchers("/api/admin/**").hasAnyRole("ADMIN")
                			.requestMatchers("/api/user/**").hasAnyRole("USER")
                			.anyRequest().authenticated())
                .oauth2ResourceServer(oa->oa.jwt(Customizer.withDefaults()))
               // .addFilterBefore(new JwtFilter(utilisateurService, jwtUtils), UsernamePasswordAuthenticationFilter.class)
                .build();

    }
	

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())
		.headers(headers -> headers.frameOptions(FrameOptionsConfig::disable))
		//.authorizeHttpRequests(request -> request.requestMatchers(toH2Console()).permitAll())
		.authorizeHttpRequests(request -> request.requestMatchers("/api/auth").permitAll())
		.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		return http.build();
	}
	
	
  public UserDetailsService userDetailsService() {

      UserDetails user1 = User
              .withDefaultPasswordEncoder()
              .username("user")
              .password("12345")
              .roles("USER")
              .build();

      UserDetails user2 = User
              .withDefaultPasswordEncoder()
              .username("admin")
              .password("12345")
              .roles("ADMIN")
              .build();
      return new InMemoryUserDetailsManager(user1, user2);
  }
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
	    CorsConfiguration configuration = new CorsConfiguration();
	    configuration.setAllowedOrigins(Arrays.asList("*"));
	    //configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE","OPTIONS"));
	    configuration.setAllowedMethods(Arrays.asList("*"));
	    configuration.setAllowedHeaders(Arrays.asList("*"));
	    configuration.setExposedHeaders(List.of("x-auth-token"));
	    //configuration.setAllowCredentials(true);
	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", configuration);
	    return source;
	}
    	/*return http.authorizeHttpRequests(auth -> {
            auth.requestMatchers("/admin").hasRole("ADMIN");
            auth.requestMatchers("/user").hasRole("USER");
            auth.anyRequest().authenticated();
        }).formLogin(Customizer.withDefaults()).build();
    }
    */
  /*  @Bean
    public UtilisateurDetailsService users() {
        UserDetails user = User.builder()
                .username("developdev216@gmail.com")
                .password(passwordEncoder().encode("developer"))
                .roles("USER").build();
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("USER", "ADMIN").build();
        return new InMemoryUserDetailsManager(user, admin);
    }
    */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    JwtEncoder jwtEncoder(){
    	return new NimbusJwtEncoder(new ImmutableSecret<>(secretKey.getBytes()));
    }
    
    @Bean
    JwtDecoder jwtDecoder() {
    	SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "RSA");
    	return NimbusJwtDecoder.withSecretKey(secretKeySpec).macAlgorithm(MacAlgorithm.HS512).build();
    }
    
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(utilisateurDetailsService);
        return new ProviderManager(daoAuthenticationProvider);
        
    	
    }
    
//    @Bean
//    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService) {
//    	DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//    	daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
//    	daoAuthenticationProvider.setUserDetailsService(userDetailsService);
//    	return new ProviderManager(daoAuthenticationProvider);
//    }
    

}