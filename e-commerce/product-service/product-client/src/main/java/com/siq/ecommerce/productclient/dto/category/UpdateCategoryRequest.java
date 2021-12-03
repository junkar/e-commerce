package com.siq.ecommerce.productclient.dto.category;

import lombok.*;

@Value
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class UpdateCategoryRequest {
    String name;
}
