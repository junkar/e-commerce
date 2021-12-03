package com.siq.ecommerce.buyingservice.dto.buying;

import lombok.*;

@Value
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class ChangeInBuyingDto {
    Long productId;
    Integer quantity;
}
