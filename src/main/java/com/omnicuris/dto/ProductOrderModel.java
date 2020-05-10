package com.omnicuris.dto;

import lombok.Data;

@Data
public class ProductOrderModel {

	private long categoryId;
	private String productName;
	private double productPrice;
	private String orderSize;
	private int numberOfItems;
	private String orderShipName;
	private String orderAddress;
	private String orderAddress2;
	private String orderCity;
	private String orderState;
	private int orderZip;
	private String orderCountry;
	private String emailId;
	private String mobile;
}
