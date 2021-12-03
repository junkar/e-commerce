package com.siq.ecommerce.productserver.converter;

import com.siq.ecommerce.productclient.dto.category.*;
import com.siq.ecommerce.productserver.model.*;
import lombok.*;
import org.springframework.stereotype.*;

import java.util.stream.*;

@Component
@AllArgsConstructor
public class CategoryDtoConverter {

    private final ProductDtoConverter productDtoConverter;

    public CategoryDto convert(Category from) {
        return new CategoryDto(from.id,
                from.name,
                from.getProducts()
                        .stream()
                        .map(productDtoConverter::convert )
                        .collect(Collectors.toSet()));
    }
}
