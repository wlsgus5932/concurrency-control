package com.example.api.repository;

import com.example.api.domain.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRespository extends JpaRepository<Coupon, Long> {
}
