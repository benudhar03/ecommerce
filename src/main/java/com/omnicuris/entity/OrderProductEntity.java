package com.omnicuris.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import lombok.Data;

@Data
@Entity
@Table(name = "order_product")
//@SequenceGenerator(name="order_seq", sequenceName = "order_sequence", initialValue=1, allocationSize=1)
public class OrderProductEntity {

	@Id
	@Column(name = "order_id")
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long orderId;
	
	@Column(name = "category_id")
	private long categoryId;
	
	@Column(name = "product_name")
	private String productName;
	
	@Column(name = "product_price")
	private double productPrice;
	
	@Column(name = "order_amount")
	private double orderAmount;
	
	@Column(name = "order_size")
	private String orderSize;
	
	@Column(name = "number_of_items")
	private int numberOfItems;
	
	@Column(name = "ordership_name")
	private String orderShipName;
	
	@Column(name = "order_address")
	private String orderAddress;
	
	@Column(name = "order_addresstwo")
	private String orderAddress2;
	
	@Column(name = "order_city")
	private String orderCity;
	
	@Column(name = "order_state")
	private String orderState;
	
	@Column(name = "order_zip")
	private int orderZip;
	
	@Column(name = "order_country")
	private String orderCountry;
	
	@Column(name = "email_id")
	private String emailId;
	
	@Column(name = "mobile")
	private String mobile;
	
	@Column(name = "order_date")
	@CreationTimestamp
	private Date orderDate;
	
	@Column(name = "tracking_number")
	private long trackingNumber;
}
