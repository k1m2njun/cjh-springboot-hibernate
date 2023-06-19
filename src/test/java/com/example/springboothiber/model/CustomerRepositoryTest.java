package com.example.springboothiber.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import javax.persistence.EntityManager;
import java.util.List;

@Import(CustomerRepository.class)
@DataJpaTest // 테스트 직후 롤백 됨.
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private EntityManager em;

    @BeforeEach
    public void setUp(){
        Customer customer = Customer.builder()
                .name("홍길동")
                .tel("0103333")
                .build();
        customerRepository.save(customer);

        em.clear(); // Hibernate 저장공간 초기화. 캐싱 안됨. 그러니 DB로 전달할 조회 쿼리를 만들어서 전달함.
    }

    @Test
    public void findById_test() {
        // given
        Long id = 1L;

        // when
        Customer customerPS = customerRepository.findById(id); // DB에서 들고온 데이터 PS
        System.out.println("테스트 : " + customerPS.getName());

        // then

    }

    @Test
    public void save_test() {
        // given
        Customer customer = Customer.builder()
                .name("홍길동")
                .tel("0102222")
                .build();

        // when
        customerRepository.save(customer);

        // then
    }

    @Test
    public void findAll() {
        // given
        Customer customer = Customer.builder()
                .name("임꺽정")
                .tel("01011251252")
                .build();
        customerRepository.save(customer);

        // when
        List<Customer> customerLisT = customerRepository.findAll();
        System.out.println("테스트 : " + customerLisT.size());

        // then
    }

}
