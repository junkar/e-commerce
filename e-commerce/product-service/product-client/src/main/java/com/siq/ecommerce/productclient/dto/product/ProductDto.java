package com.siq.ecommerce.productclient.dto.product;

import com.siq.ecommerce.productclient.dto.category.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class ProductDto {
    private Long id;
    private String name;
    private Long categoryId;
    private Double price;
    private Integer count;
}
