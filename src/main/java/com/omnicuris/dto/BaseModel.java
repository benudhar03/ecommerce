package com.omnicuris.dto;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@MappedSuperclass
public class BaseModel 
{
	@NotNull
	private String createdBy;
	
	@NotNull
	private String updatedBy;
}
