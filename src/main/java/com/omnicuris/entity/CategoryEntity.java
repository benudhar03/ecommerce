package com.omnicuris.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "category")
//@SequenceGenerator(name="category_seq", sequenceName = "category_sequence", initialValue=1, allocationSize=1)
public class CategoryEntity extends BaseEntity{

	@Id
	@Column(name = "category_id")
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_seq")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long categoryId;
	
	@Column(name = "category_name")
	private String categoryName;
	
	@Column(name = "category_desc")
	private String categoryDesc;
	
}
