package gwr.library.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Data;

/**
 * The Class Role.
 */
@Entity
@Data
public class Role extends BaseEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	private Integer id;
	
	/** The name. */
	private String name;

	/** The users. */
	@ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY) // variable mapping of class Student
	private Set<Users> users;
}
