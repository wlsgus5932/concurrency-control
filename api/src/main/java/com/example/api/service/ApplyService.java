package com.example.api.service;

import com.example.api.producer.CouponCreateProducer;
import com.example.api.repository.AppliedUserRepository;
import com.example.api.repository.CouponCountRespoistory;
import com.example.api.repository.CouponRespository;
import org.springframework.stereotype.Service;

@Service
public class ApplyService {
    private final CouponRespository couponRespository;
    private final CouponCountRespoistory couponCountRespoistory;
    private final CouponCreateProducer couponCreateProducer;
    private final AppliedUserRepository appliedUserRepository;

    public ApplyService(CouponRespository couponRespository, CouponCountRespoistory couponCountRespoistory, CouponCreateProducer couponCreateProducer, AppliedUserRepository appliedUserRepository) {
        this.couponRespository = couponRespository;
        this.couponCountRespoistory = couponCountRespoistory;
        this.couponCreateProducer = couponCreateProducer;
        this.appliedUserRepository = appliedUserRepository;
    }

    public void apply(Long userId) {
        //lock start
        //쿠폰발급 여부
        //if(발급됐다면) return
        Long apply = appliedUserRepository.add(userId);

        if(apply != 1) {
            return;
        }

        Long count = couponCountRespoistory.increment();
        //레이스 컨디션 문제

        if(count > 100) {
            return;
        }

        couponCreateProducer.create(userId);
        //lock end
    }
}
