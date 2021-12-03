package com.siq.ecommerce.productclient.dto.category;

import com.siq.ecommerce.productclient.dto.product.*;
import lombok.*;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    private Long id;
    private String name;
    private Set<ProductDto> products;
}