package com.omnicuris.dto;

import java.util.List;
import lombok.Data;

@Data
public class OrderedProduct {

	private List<ProductOrderModel> productOrder;
}
