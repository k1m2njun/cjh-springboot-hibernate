package com.example.springboothiber.model;

import org.springframework.data.jpa.repository.JpaRepository;

// 컴포넌트 스캔 생략 가능 -> JpaRepository
public interface CustomerJpaRepository extends JpaRepository<Customer, Long> {
}
