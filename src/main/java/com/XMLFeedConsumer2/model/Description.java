package com.XMLFeedConsumer2.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Table
@Entity
@ToString
public class Description {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 16383)
    private String other_info;
    @Column(length = 511)
    private String param;
}
