package com.omnicuris.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "product_option")
//@SequenceGenerator(name="option_seq", sequenceName = "option_sequence", initialValue=1, allocationSize=1)
public class ProductOptionEntity {

	@Id
	@Column(name = "option_id")
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "option_seq")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long optionId;
	
	@Column(name = "option_name")
	private String name;
	
	
}
