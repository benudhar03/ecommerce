package com.omnicuris.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.omnicuris.dto.CategoryModel;
import com.omnicuris.dto.OrderedProduct;
import com.omnicuris.dto.ProductModel;
import com.omnicuris.dto.ProductOptionModel;
import com.omnicuris.dto.ProductOrderModel;
import com.omnicuris.dto.UpdateProductModel;
import com.omnicuris.entity.CategoryEntity;
import com.omnicuris.entity.OrderProductEntity;
import com.omnicuris.entity.ProductEntity;
import com.omnicuris.entity.ProductOptionEntity;
import com.omnicuris.repository.CategoryRepository;
import com.omnicuris.repository.OderRepository;
import com.omnicuris.repository.ProductRepository;
import com.omnicuris.resp.dto.AbstractResponse;
import com.omnicuris.resp.dto.OrderResponse;
import com.omnicuris.resp.dto.OrderResponseModel;
import com.omnicuris.resp.dto.ProductRespModel;
import com.omnicuris.resp.dto.ProductResponse;
import com.omnicuris.resp.dto.StatusResponse;

@Service
public class EcomServiceImpl implements EcomService{

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OderRepository oderRepository;
	
	@Override
	public ResponseEntity<? extends AbstractResponse> createCategory(CategoryModel category) {
		try {
			CategoryEntity entity = new CategoryEntity();
			category.setCategoryName(category.getCategoryName().toUpperCase());
			BeanUtils.copyProperties(category, entity);
			categoryRepository.save(entity);
			return new ResponseEntity<>(new StatusResponse(1,"Category Created Successfully"),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new StatusResponse(0,"Failed"),HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@Override
	public ResponseEntity<? extends AbstractResponse> createProduct(ProductModel product) {
		try {
			ProductEntity entity = new ProductEntity();
			List<ProductOptionEntity> listOfProductOptions = new ArrayList<>();
			product.setProductName(product.getProductName().toUpperCase());
			BeanUtils.copyProperties(product, entity);
			product.getProductOptions().forEach(new Consumer<ProductOptionModel>() {
				@Override
				public void accept(ProductOptionModel option) {
					ProductOptionEntity options = new ProductOptionEntity();
					options.setName(option.getName().toUpperCase());
					listOfProductOptions.add(options);
				}
			});
			entity.setCategory(categoryRepository.findById(product.getCategoryId()).get());
			entity.setOptions(listOfProductOptions);
			productRepository.save(entity);
			return new ResponseEntity<>(new StatusResponse(1,"Product Created Successfully"),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new StatusResponse(0,"Failed"),HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@Override
	public ResponseEntity<? extends AbstractResponse> gatProductDetails() {
		try {
			ProductResponse resp = new ProductResponse();
			List<ProductRespModel> listOfProducts = new ArrayList<>();
			List<ProductEntity> listOfproducts = productRepository.findAll();
			listOfproducts.forEach(new Consumer<ProductEntity>() {
				@Override
				public void accept(ProductEntity entity) {
					System.out.println(entity.toString());
					ProductRespModel product =  new ProductRespModel();
					product.setProductId(entity.getProductId());
					product.setProductName(entity.getProductName());
					product.setProductPrice(entity.getProductPrice());
					product.setProductUnits(entity.getProductUnits());
					product.setProductShortDesc(entity.getProductShortDesc());
					product.setProductLongDesc(entity.getProductLongDesc());					
					product.setCategoryName(entity.getCategory().getCategoryName());
					product.setCategoryId(entity.getCategory().getCategoryId());
					List<String> listOfSize = entity.getOptions().stream().map(options-> options.getName()).collect(Collectors.toList());
					product.setSize(listOfSize);
					listOfProducts.add(product);
				}
			});
			resp.setListOfProducts(listOfProducts);
			return new ResponseEntity<>(resp,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new StatusResponse(0,"Failed"),HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@Override
	public ResponseEntity<? extends AbstractResponse> updateProduct(UpdateProductModel updateProductModel) {
		try {
			if (!productRepository.existsById(updateProductModel.getId())) {
				System.out.println("Inside");
				return new ResponseEntity<>(new StatusResponse(201, "Failed to update product details product-Id does not found"),
						HttpStatus.NOT_FOUND);
			}else {
				List<ProductOptionEntity> listOfProductOptions = new ArrayList<>();
				ProductEntity entity = productRepository.findByProductId(updateProductModel.getId());
				entity.setProductName(updateProductModel.getProductName().toUpperCase());
				BeanUtils.copyProperties(updateProductModel, entity);
				updateProductModel.getProductOptions().forEach(new Consumer<ProductOptionModel>() {
					@Override
					public void accept(ProductOptionModel option) {
						ProductOptionEntity options = new ProductOptionEntity();
						options.setName(option.getName().toUpperCase());
						listOfProductOptions.add(options);
					}
				});
				entity.setCategory(categoryRepository.findById(updateProductModel.getCategoryId()).get());
				entity.setOptions(listOfProductOptions);
				productRepository.save(entity);
			}
			return new ResponseEntity<>(new StatusResponse(1,"Product Updated Successfully"),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new StatusResponse(0,"Failed"),HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@Override
	public ResponseEntity<? extends AbstractResponse> deleteProduct(long productId) {
		try {
			if(!productRepository.existsById(productId)) {
				return new ResponseEntity<>(new StatusResponse(201, "Failed to Delete product details product-Id does not found"),
						HttpStatus.NOT_FOUND);
			}
			ProductEntity product = productRepository.findById(productId).get();
			productRepository.delete(product);
			return new ResponseEntity<>(new StatusResponse(1,"Successfully Deleted"),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new StatusResponse(0,"Failed"),HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@Override
	public ResponseEntity<? extends AbstractResponse> productOrder(OrderedProduct orderedRequest) {
		try {
			Random rnd = new Random();			
			OrderProductEntity order = new OrderProductEntity();
			if (orderedRequest.getProductOrder().size() == 1) {				
				categoryRepository.findById(orderedRequest.getProductOrder().get(0).getCategoryId()).get();
				BeanUtils.copyProperties(orderedRequest.getProductOrder().get(0), order);				
				order.setTrackingNumber(100000 + rnd.nextInt(900000));				
				oderRepository.save(order);
			}else {
				System.err.println("Bulk Order");				
				orderedRequest.getProductOrder().forEach(new Consumer<ProductOrderModel>() {
					@Override
					public void accept(ProductOrderModel orderModel) {
						System.err.println(orderModel.getOrderShipName());
						OrderProductEntity entity = new OrderProductEntity();
						BeanUtils.copyProperties(orderModel, entity);
						oderRepository.save(entity);
					}
				});
			}
			return new ResponseEntity<>(new StatusResponse(1,"We received your order"),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new StatusResponse(0,"Failed"),HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@Override
	public ResponseEntity<? extends AbstractResponse> getAllOrders() {
		try {
			OrderResponse resp =  new OrderResponse();
			List<OrderResponseModel> listOfOrder = new ArrayList<>();
			List<OrderProductEntity> orderList = oderRepository.findAll();
			orderList.forEach(new Consumer<OrderProductEntity>() {
				@Override
				public void accept(OrderProductEntity entity) {
					OrderResponseModel model = new OrderResponseModel();
					BeanUtils.copyProperties(entity, model);
					
					listOfOrder.add(model);
				}
			});
			resp.setOrderList(listOfOrder);
			return new ResponseEntity<>(resp,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new StatusResponse(0,"Failed"),HttpStatus.NOT_ACCEPTABLE);
		}
	}

	
}
