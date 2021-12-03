package com.siq.ecommerce.productclient.dto.product;

import lombok.*;

@Value
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class UpdateProductPriceRequest {
    Double price;
}
