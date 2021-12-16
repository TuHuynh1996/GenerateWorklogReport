package gwr.library.security;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import gwr.library.repository.UserRepository;
import gwr.library.security.ultis.JwtTokenUtil;

/**
 * The Class SecurityConfiguration.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    /** The user principal details service. */
    @Autowired
    private UserPrincipalDetailsService userPrincipalDetailsService;

    /** The secret key. */
    @Value("${jwtproperties.secret}")
    private String secretKey;

    @Value("#{${security.authorize}}")
    Map<String, String[]> securityAuthorize;
    
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    /**
     * Instantiates a new security configuration.
     *
     * @param userPrincipalDetailsService the user principal details service
     * @param userRepository              the user repository
     */
    public SecurityConfiguration(UserPrincipalDetailsService userPrincipalDetailsService) {
        this.userPrincipalDetailsService = userPrincipalDetailsService;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.security.config.annotation.web.configuration.
     * WebSecurityConfigurerAdapter#configure(org.springframework.security.config.
     * annotation.authentication.builders.AuthenticationManagerBuilder)
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.security.config.annotation.web.configuration.
     * WebSecurityConfigurerAdapter#configure(org.springframework.security.config.
     * annotation.web.builders.HttpSecurity)
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // remove csrf and state in session because in jwt we do not need them
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                // add jwt filters (1. authentication, 2. authorization)
//                .addFilter(new JwtAuthenticationFilter(authenticationManager(), jwtTokenUtil))
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), this.userPrincipalDetailsService, jwtTokenUtil))
                .authorizeRequests()
                // configure access rules
                .antMatchers(securityAuthorize.get("ignore")).permitAll()
                .antMatchers(securityAuthorize.get("admin")).hasRole("admin")
                .antMatchers(securityAuthorize.get("manager")).hasRole("manager")
                .antMatchers(securityAuthorize.get("user")).hasRole("user")
                .anyRequest().authenticated();
    }

    /**
     * Authentication provider.
     *
     * @return the dao authentication provider
     */
    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(this.userPrincipalDetailsService);

        return daoAuthenticationProvider;
    }
    
    @Override @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * Password encoder.
     *
     * @return the password encoder
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
