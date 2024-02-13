package com.example.consumer.repository;

import com.example.consumer.domain.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRespository extends JpaRepository<Coupon, Long> {
}
