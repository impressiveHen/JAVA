package com.rest.employ.controller;

import com.rest.employ.entity.CustumerOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<CustumerOrder, Long> {
}
