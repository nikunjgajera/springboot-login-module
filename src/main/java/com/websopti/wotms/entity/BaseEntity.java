package com.websopti.wotms.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;

@MappedSuperclass
public abstract class BaseEntity<ID extends Serializable> implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonIgnore
	private Boolean isActive;
	
	@JsonIgnore
	private Date createdDate;
	
	@JsonIgnore
	private Date modifiedDate;
	
	@PrePersist
	public void prePersist(){
		this.createdDate	= new Date();
		this.modifiedDate	= this.createdDate;
		this.isActive = true;
	}
	
	@PreUpdate
	public void preUpdate(){
		this.modifiedDate	= new Date();
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}
}
