package com.example.springboothiber.model;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // DB에 TABLE 만들어 줌. 그래서 PK가 필수이므로 @Id를 달았다.(리플렉션으로 대신 해줌)
@Getter // Setter는 의미있게 만들어야 함.
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AutoIncrement 적용
    private Long id;
    private String name;
    private String tel;

    public Customer(){
        System.out.println("Customer 디폴트 생성자 호출");
    };

    @Builder
    public Customer(Long id, String name, String tel) {
        this.id = id;
        this.name = name;
        this.tel = tel;
        System.out.println("Customer 풀 생성자 호출"); // 만약 Entity에서 직접 new해서 테이블을 생성했다면 이 출력문이 출력되었을 것이다.
    }
}
