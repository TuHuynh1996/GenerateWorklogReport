package gwr.library.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import gwr.library.entity.base.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * The Class Role.
 */
@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper=false)
public class Role extends BaseEntity implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	private Integer id;
	
	/** The name. */
	private String name;

	/** The users. */
	@JsonIgnoreProperties 
	@ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER, cascade = CascadeType.ALL) // variable mapping of class Student
	private List<Users> users;

    @Override
    public String toString() {
        return "Role [id=" + id + ", name=" + name + "]";
    }
}
