package com.example.springboothiber.model;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@RequiredArgsConstructor
@Repository // 컴포넌트 스캔 필요함.
public class CustomerNativeQuery {
    private final EntityManager em; // IoC 컨테이너로부터 주입받을 것. @Entity 붙어있는 클래스만 관리해주는 매니져.

    @Transactional
    public void save(Customer customer) {
        Query query = em.createNativeQuery("INSERT INTO customer (name, tel) VALUES (:name, :tel)");
        query.setParameter("name", customer.getName());
        query.setParameter("tel", customer.getTel());
        query.executeUpdate();
    }

    @Transactional
    public void update(Customer customer) {
        Query query = em.createNativeQuery("UPDATE customer SET name = :name, tel = :tel WHERE id = :id");
        query.setParameter("name", customer.getName());
        query.setParameter("tel", customer.getTel());
        query.setParameter("id", customer.getId());
        query.executeUpdate();
    }

    @Transactional
    public void delete(Customer customer) {
        Query query = em.createNativeQuery("DELETE FROM customer WHERE id = :id");
        query.setParameter("id", customer.getId());
        query.executeUpdate();
    }

    public Customer findById(Long id) {
        Query query = em.createNativeQuery("SELECT * FROM customer WHERE id = :id", Customer.class);
        query.setParameter("id", id);
        return (Customer) query.getSingleResult();
    }

    public List<Customer> findAll() {
        Query query = em.createNativeQuery("select id, name, tel from customer", Customer.class);
        return query.getResultList();
    }
}
