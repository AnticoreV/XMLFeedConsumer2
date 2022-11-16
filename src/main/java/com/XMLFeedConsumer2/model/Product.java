package com.XMLFeedConsumer2.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer product_id;
    @Column(length = 1023)
    private String names;
    private String countries;
    private String part_number;
}
