package gwr.application.dto;

import java.util.List;

import lombok.Data;

@Data
public class UserDto {

    /** The id. */
    private Integer id;

    /** The user name. */
    private String userName;

    /** The roles. */
    private List<String> roles;
}
