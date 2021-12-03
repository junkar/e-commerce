package com.siq.ecommerce.productserver.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.*;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="product_seq")
    @SequenceGenerator(name = "product_seq",sequenceName = "product_seq_table")
    @Column(name = "id", unique = true, nullable = false)
    @Setter(AccessLevel.NONE)
    private long id;

    @Column(unique=true)
    @NonNull
    private String name;

    @ManyToOne
    @JoinColumn(name="category_id", nullable=false)
    private Category category;

    @Min(value = 0, message = "Price need to be equal to or greater than 0")
    private Double price;

    @Min(value = 0, message = "Product Count need to be equal to or greater than 0")
    private Integer count;

    public Product(String name, Category category, Double price) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.count = 1;
    }

}
