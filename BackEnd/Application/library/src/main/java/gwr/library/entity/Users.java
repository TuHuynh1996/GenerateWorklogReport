package gwr.library.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import gwr.library.entity.base.BaseEntity;
import lombok.Data;

/**
 * The Class Users.
 */
@Entity
@Data
public class Users extends BaseEntity implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	/** The name. */
	private String name;
	
	/** The password. */
	private String password;

	/** The roles. */
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", // name of middlemen table
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;
}
