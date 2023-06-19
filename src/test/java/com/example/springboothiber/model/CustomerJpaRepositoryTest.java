package com.example.springboothiber.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;

@DataJpaTest
public class CustomerJpaRepositoryTest {

    @Autowired
    private CustomerJpaRepository customerJpaRepository;

    @Autowired
    private EntityManager em;

    @BeforeEach
    public void setUp(){
        Customer customer = Customer.builder()
                .name("홍길동")
                .tel("0103333")
                .build();
        customerJpaRepository.save(customer);

        em.clear(); // Hibernate 저장공간 초기화. 캐싱 안됨. 그러니 DB로 전달할 조회 쿼리를 만들어서 전달함.
    }

    @Test
    public void findById_test() {
        // given
        Long id = 1L;

        // when
        customerJpaRepository.findById(id);

        // then
    }
}
