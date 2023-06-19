package com.example.springboothiber.model;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class CustomerRepository {

    private EntityManager em;

    public CustomerRepository(EntityManager em) {
        this.em = em;
    }

    @Transactional
    public void save(Customer customer) {
        em.persist(customer);
    }

    @Transactional
    public void update(Customer customer) {
        em.merge(customer);
    }

    @Transactional
    public void delete(Customer customer) {
        em.remove(customer);
    }

    public Customer findById(Long id) {
        return em.find(Customer.class, id);
    }

    public List<Customer> findAll() {
        // 이 쿼리의 Customer는 테이블이 아니라 클래스(@Entity 붙은).-> 그럼 이 클래스의 모든 속성을 SELECT 한다.
        return em.createQuery("select ct from Customer ct", Customer.class)
                .getResultList();
    }
}
