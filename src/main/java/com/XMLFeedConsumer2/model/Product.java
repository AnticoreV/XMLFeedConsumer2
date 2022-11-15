package com.XMLFeedConsumer2.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer product_id;
    @Column(length = 1023)
    private String names;
//    @OneToOne(mappedBy = "product",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private Description description;
}
