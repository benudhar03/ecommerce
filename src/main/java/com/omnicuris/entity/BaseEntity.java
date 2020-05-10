package com.omnicuris.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@MappedSuperclass
public class BaseEntity 
{
	@Column(name="created_by")
	private String createdBy;
		
	@Column(name = "created_on")
	@CreationTimestamp
	private Date createdOn;
			
	@Column(name="updated_by")
	private String updatedBy;
		
	@Column(name = "updated_on")
	@CreationTimestamp
	private Date updatedOn;
	
}
