package com.siq.ecommerce.productserver.converter;

import com.siq.ecommerce.productclient.dto.product.*;
import com.siq.ecommerce.productserver.model.*;
import lombok.*;
import org.springframework.stereotype.*;

@Component
@AllArgsConstructor
public class ProductDtoConverter {

    public ProductDto convert(Product from) {
        return new ProductDto(from.getId(),
                from.getName(),
                from.getCategory().id,
                from.getPrice(),
                from.getCount());
    }

}
