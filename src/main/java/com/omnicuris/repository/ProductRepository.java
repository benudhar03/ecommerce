package com.omnicuris.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.omnicuris.dto.ProductModel;
import com.omnicuris.entity.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long>{

	ProductEntity findByProductId(long id);

}
