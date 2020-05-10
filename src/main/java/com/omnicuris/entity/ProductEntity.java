package com.omnicuris.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "product")
//@SequenceGenerator(name="product_seq", sequenceName = "product_sequence", initialValue=1, allocationSize=1)
public class ProductEntity extends BaseEntity{

	@Id
	@Column(name = "product_id")
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long productId;
	
	@Column(name = "product_name")
	private String productName;
	
	@Column(name = "product_price")
	private double productPrice;
	
	@Column(name = "product_units")
	private long productUnits;
	
	@Column(name = "short_desc")
	private String productShortDesc;
	
	@Column(name = "long_desc")
	private String productLongDesc;
	
	@JoinColumn(name = "category_id")
	@OneToOne(fetch = FetchType.LAZY, cascade = {
			CascadeType.PERSIST.DETACH		
	})
	private CategoryEntity category;
	
	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name = "product_id",referencedColumnName = "product_id")
	private List<ProductOptionEntity> options;
	
}
