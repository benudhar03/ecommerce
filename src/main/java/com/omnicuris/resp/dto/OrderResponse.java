package com.omnicuris.resp.dto;

import java.util.List;

import lombok.Data;

@Data
public class OrderResponse extends AbstractResponse {
	
	private List<OrderResponseModel> orderList;
}
