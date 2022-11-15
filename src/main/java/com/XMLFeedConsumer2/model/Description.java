package com.XMLFeedConsumer2.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Table
@Entity
public class Description {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 4095)
    private String info;
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "id")
//    private Product product;
}
