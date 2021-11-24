package gwr.library.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import gwr.library.entity.base.BaseEntity;
import lombok.Data;

/**
 * The Class Role.
 */
@Entity
@Data
public class Role extends BaseEntity implements Serializable{

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
