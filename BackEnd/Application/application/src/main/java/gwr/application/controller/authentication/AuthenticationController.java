package gwr.application.controller.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import gwr.application.model.authentication.AuthenticationRequest;
import gwr.application.model.authentication.AuthenticationResponse;
import gwr.library.security.UserPrincipal;
import gwr.library.security.ultis.JwtTokenUtil;

/**
 * The Class AuthenticationController.
 */
@RestController
public class AuthenticationController {

    /** The authentication manager. */
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    /**
     * Login.
     *
     * @param request the request
     * @return the string
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest request) {
        try {
            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));

            UserPrincipal principal = (UserPrincipal) authenticate.getPrincipal();

            String token = jwtTokenUtil.generateToken(principal);
            AuthenticationResponse response = new AuthenticationResponse();
            response.setJwt(token);
            return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, token).body(response);
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Wrong user name or password");
        }
    }
}
