package gwr.library.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import gwr.library.entity.base.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * The Class Users.
 */
@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper=false)
public class Users extends BaseEntity implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	/** The name. */
	private String userName;
	
	/** The password. */
	private String password;

	/** The roles. */
	@JsonIgnoreProperties 
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", // name of middlemen table
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<Role> roles;

    @Override
    public String toString() {
        return "Users [id=" + id + ", userName=" + userName + ", password=" + password + ", roles=" + roles + "]";
    }
}
