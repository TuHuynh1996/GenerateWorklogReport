package gwr.library.entity.base;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import gwr.library.security.ultis.SecurityContextHolderUlti;
import lombok.Data;

/**
 * Instantiates a new base entity.
 */
@Data
@MappedSuperclass
public abstract class BaseEntity {

	/** The create by. */
	@Column(name = "create_by")
	private String createBy;

	/** The update by. */
	@Column(name = "update_by")
	private String updateBy;

	/** The create date. */
	@CreatedDate
	@Column(name = "create_date")
	private LocalDateTime createDate;

	/** The updata date. */
	@LastModifiedDate
	@Column(name = "updata_date")
	private LocalDateTime updataDate;
	
    /**
     * Pre persist.
     */
    @PrePersist
    protected void prePersist() {
    	String userNm = SecurityContextHolderUlti.getCurrentUserName();
    	this.createBy = userNm;
    	this.updateBy = userNm;
    	this.createDate = LocalDateTime.now();
    	this.updataDate = LocalDateTime.now();
    }

    /**
     * Pre update.
     */
    @PreUpdate
    protected void preUpdate() {
    	String userNm = SecurityContextHolderUlti.getCurrentUserName();
        this.updateBy = userNm;
        this.updataDate = LocalDateTime.now();
    }
    
    /**
     * Pre remove.
     */
    @PreRemove
    protected void preRemove() {
    	// TODO: I don't know
    }
}
