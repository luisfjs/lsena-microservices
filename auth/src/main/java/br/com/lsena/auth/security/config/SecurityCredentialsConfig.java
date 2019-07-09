package br.com.lsena.auth.security.config;

import br.com.lsena.auth.security.flter.JwtUserNameAndPasswordAuthenticationFilter;
import br.com.lsena.core.property.JwtConfiguration;
import br.com.lsena.security.config.SecurityTokenConfig;
import br.com.lsena.security.token.creator.TokenCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityCredentialsConfig extends SecurityTokenConfig {

    private final TokenCreator tokenCreator;
    private final UserDetailsService userDetailsService;

    public SecurityCredentialsConfig(JwtConfiguration jwtConfiguration,
                                     @Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService,
                                     TokenCreator tokenCreator) {
        super(jwtConfiguration);
        this.tokenCreator = tokenCreator;
        this.userDetailsService = userDetailsService;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilter(new JwtUserNameAndPasswordAuthenticationFilter(authenticationManager(), jwtConfiguration, tokenCreator));
        super.configure(http);

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
