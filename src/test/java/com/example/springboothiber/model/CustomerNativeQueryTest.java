package com.example.springboothiber.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@Import(CustomerNativeQuery.class) // 직접 만든 것들은 import 해야 한다.
@DataJpaTest
public class CustomerNativeQueryTest {

    @Autowired
    private CustomerNativeQuery customerNativeQuery;

    @BeforeEach
    public void setUp(){
        Customer customer = Customer.builder()
                .name("코스")
                .tel("0103333")
                .build();
        customerNativeQuery.save(customer);
    }

    @Test
    public void save_test(){
        // given
        Customer customer = Customer.builder()
                .name("홍길동")
                .tel("0102222")
                .build();

        // when
        customerNativeQuery.save(customer);

        // then
        Customer customerPS = customerNativeQuery.findById(1L);
        Assertions.assertThat(customerPS.getName()).isEqualTo("코스");

    } // rollback

    @Test
    public void findById_test(){
        // given
        Long id = 1L;

        // when
        Customer customerPS = customerNativeQuery.findById(id);

        // then
        Assertions.assertThat(customerPS.getName()).isEqualTo("코스");
    }
}
