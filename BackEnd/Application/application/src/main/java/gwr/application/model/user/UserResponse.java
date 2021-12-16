package gwr.application.model.user;

import java.util.List;

import lombok.Data;

/**
 * Instantiates a new user list response.
 */
@Data
public class UserResponse {

    /** The id. */
    private Integer id;
    
    /** The user name. */
    private String userName;
    
    /** The roles. */
    private List<String> roles;
}
