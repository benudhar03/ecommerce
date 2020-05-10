package com.omnicuris.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omnicuris.dto.CategoryModel;
import com.omnicuris.dto.OrderedProduct;
import com.omnicuris.dto.ProductModel;
import com.omnicuris.dto.ProductOrderModel;
import com.omnicuris.dto.UpdateProductModel;
import com.omnicuris.resp.dto.AbstractResponse;
import com.omnicuris.service.EcomService;

import lombok.Delegate;

@RestController
@RequestMapping("ecommerce_rest/api")
public class EcomController {
	
	@Autowired
	EcomService ecomService;
	
	@PostMapping("/createCategory")
	public ResponseEntity<? extends AbstractResponse> createCategory(@RequestBody CategoryModel category){
		return ecomService.createCategory(category);
	}
	
	@PostMapping("/createProduct")
	public ResponseEntity<? extends AbstractResponse> createProduct(@RequestBody ProductModel product){
		return ecomService.createProduct(product);
	}
	
	@GetMapping("/getProducts")
	public ResponseEntity<? extends AbstractResponse> gatProductDetails(){
		return ecomService.gatProductDetails();
	}
	
	@PutMapping("/updateProduct")
	public ResponseEntity<? extends AbstractResponse> updateProduct(@RequestBody UpdateProductModel updateProductModel){
		return ecomService.updateProduct(updateProductModel);
	}
	
	@DeleteMapping("/deleteProduct/{productId}")
	public ResponseEntity<? extends AbstractResponse> deleteProduct(@PathVariable long productId){
		return ecomService.deleteProduct(productId);
	}
	
	
	@PostMapping("/orderedProduct")
	public ResponseEntity<? extends AbstractResponse> productOrder(@RequestBody OrderedProduct orderedProductRequest){
		return ecomService.productOrder(orderedProductRequest);
	}
	
	
	@GetMapping("/getAllOrders")
	public ResponseEntity<? extends AbstractResponse> getAllOrders(){
		return ecomService.getAllOrders();
	}
	
}
