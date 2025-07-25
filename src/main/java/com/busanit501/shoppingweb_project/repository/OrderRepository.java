package com.busanit501.shoppingweb_project.repository;

import com.busanit501.shoppingweb_project.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("select b from Order b where b.orderId = :orderId")
    Order findByOrderId(@Param("orderId")Long orderId);

}
