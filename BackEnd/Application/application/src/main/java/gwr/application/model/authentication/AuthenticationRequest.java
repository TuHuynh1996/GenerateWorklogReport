package gwr.application.model.authentication;

import lombok.Data;

/**
 * Instantiates a new authentication request.
 */
@Data
public class AuthenticationRequest {

    /** The user name. */
    private String userName;
    
    /** The password. */
    private String password;
}
