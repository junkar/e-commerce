package com.siq.ecommerce.buyingservice.dto.buying;

import com.siq.ecommerce.productclient.dto.product.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BuyingDetailDto {
    private Long id;
    private ProductDto product;
    private Integer quantity;
}
