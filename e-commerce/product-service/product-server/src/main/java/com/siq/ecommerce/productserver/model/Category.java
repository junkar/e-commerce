package com.siq.ecommerce.productserver.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.*;
import java.util.*;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="category_seq")
    @SequenceGenerator(name = "category_seq",sequenceName = "category_seq_table")
    @Column(name = "id", unique = true, nullable =   false)
    @Setter(AccessLevel.NONE)
    public long id;

    @Column(unique=true)
    @NonNull
    @NotBlank
    public String name;

    @OneToMany(mappedBy = "category")
    private Set<Product> products = Collections.emptySet();

    public Category(String name) {
        this.name = name;
    }
}
