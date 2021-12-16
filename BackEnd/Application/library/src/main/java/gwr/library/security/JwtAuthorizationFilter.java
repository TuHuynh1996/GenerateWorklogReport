package gwr.library.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import gwr.library.security.ultis.JwtTokenUtil;

/**
 * The Class JwtAuthorizationFilter.
 */
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    /** The user repository. */

    /** The secret key. */
    private JwtTokenUtil jwtTokenUtil;

    private UserPrincipalDetailsService userDetailsService;

        /**
     * Instantiates a new jwt authorization filter.
     *
     * @param authenticationManager the authentication manager
     * @param userRepository        the user repository
     */
    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserPrincipalDetailsService userDetailsService,
            JwtTokenUtil jwtTokenUtil) {
        super(authenticationManager);
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.security.web.authentication.www.BasicAuthenticationFilter
     * #doFilterInternal(javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain)
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // Read the Authorization header, where the JWT token should be
        String header = request.getHeader("Authorization");

        // If header does not contain BEARER or is null delegate to Spring impl and exit
        if (header == null || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        // If header is present, try grab user principal from database and perform
        // authorization
        Authentication authentication = getUsernamePasswordAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Continue filter execution
        chain.doFilter(request, response);
    }

    /**
     * Gets the username password authentication.
     *
     * @param request the request
     * @return the username password authentication
     */
    private Authentication getUsernamePasswordAuthentication(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        // parse the token and validate it
        String userName = jwtTokenUtil.getUserName(token);

        // Search in the DB if we find the user by token subject (username)
        // If so, then grab user details and create spring auth token using username,
        // pass, authorities/roles
        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserPrincipal userDetails = (UserPrincipal)this.userDetailsService.loadUserByUsername(userName);
            
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());

            return auth;
        }
        return null;

    }
}
