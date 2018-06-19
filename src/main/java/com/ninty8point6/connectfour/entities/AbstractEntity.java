package com.ninty8point6.connectfour.entities;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

import javax.persistence.*;

/**
 * The type Abstract entity.
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AbstractEntity {

	@CreatedDate
	private Date createdDate;

	@LastModifiedDate
	private Date updatedDate;
	
	@Version
	private Long version;

    /**
     * Gets created date.
     *
     * @return the created date
     */
    public Date getCreatedDate() {
		return createdDate;
	}

    /**
     * Sets created date.
     *
     * @param createdDate the created date
     */
    public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

    /**
     * Gets updated date.
     *
     * @return the updated date
     */
    public Date getUpdatedDate() {
		return updatedDate;
	}

    /**
     * Sets updated date.
     *
     * @param updatedDate the updated date
     */
    public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

    /**
     * Gets version.
     *
     * @return the version
     */
    public Long getVersion() {
		return version;
	}

    /**
     * Sets version.
     *
     * @param version the version
     */
    public void setVersion(Long version) {
		this.version = version;
	}
	
	
	
}
