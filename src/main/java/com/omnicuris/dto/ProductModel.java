package com.omnicuris.dto;

import java.util.List;
import lombok.Data;

@Data
public class ProductModel extends BaseModel{

	private String productName;
	private double productPrice;
	private long productUnits;
	private String productShortDesc;
	private String productLongDesc;
	private long categoryId;
	private List<ProductOptionModel> productOptions;
}
