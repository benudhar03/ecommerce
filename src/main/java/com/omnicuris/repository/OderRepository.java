package com.omnicuris.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.omnicuris.entity.OrderProductEntity;

@Repository
public interface OderRepository extends JpaRepository<OrderProductEntity, Long>{

}
