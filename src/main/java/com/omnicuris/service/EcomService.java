package com.omnicuris.service;

import org.springframework.http.ResponseEntity;
import com.omnicuris.dto.CategoryModel;
import com.omnicuris.dto.OrderedProduct;
import com.omnicuris.dto.ProductModel;
import com.omnicuris.dto.ProductOrderModel;
import com.omnicuris.dto.UpdateProductModel;
import com.omnicuris.resp.dto.AbstractResponse;

public interface EcomService {

	ResponseEntity<? extends AbstractResponse> createCategory(CategoryModel category);

	ResponseEntity<? extends AbstractResponse> createProduct(ProductModel product);

	ResponseEntity<? extends AbstractResponse> gatProductDetails();

	ResponseEntity<? extends AbstractResponse> updateProduct(UpdateProductModel updateProductModel);

	ResponseEntity<? extends AbstractResponse> deleteProduct(long id);

	ResponseEntity<? extends AbstractResponse> productOrder(OrderedProduct orderedProduct);

	ResponseEntity<? extends AbstractResponse> getAllOrders();

}
