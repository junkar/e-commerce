package com.siq.ecommerce.buyingservice.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.*;


@Setter
@Getter
@Entity
@NoArgsConstructor
public class BuyingDetail  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="buying_detail_seq")
    @SequenceGenerator(name = "buying_detail_seq",sequenceName = "buying_detail_seq_table")
    @Column(name = "id", unique = true, nullable = false)
    @Setter(AccessLevel.NONE)
    public long id;

    @Column(name = "buying_id")
    @Setter(AccessLevel.NONE)
    private @NotNull Long buyingId;

    @Column(name = "product_id")
    @Setter(AccessLevel.NONE)
    private @NotNull Long productId;

    @Min(value = 0, message = "Quantity of product cannot be less then 0")
    private Integer quantity = 0;

    public BuyingDetail(Long buyingId, Long productId) {
        this.buyingId = buyingId;
        this.productId = productId;
    }
}
