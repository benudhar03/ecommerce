package com.omnicuris.resp.dto;

import java.util.List;

import lombok.Data;

@Data
public class ProductResponse extends AbstractResponse{

	private List<ProductRespModel> listOfProducts;
}
