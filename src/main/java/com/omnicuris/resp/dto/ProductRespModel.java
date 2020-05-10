package com.omnicuris.resp.dto;

import java.util.List;
import lombok.Data;

@Data
public class ProductRespModel{

	private long productId;
	private String productName;
	private double productPrice;
	private long productUnits;
	private String productShortDesc;
	private String productLongDesc;
	private String categoryName;
	private long categoryId;
	private List<String> size;
}
