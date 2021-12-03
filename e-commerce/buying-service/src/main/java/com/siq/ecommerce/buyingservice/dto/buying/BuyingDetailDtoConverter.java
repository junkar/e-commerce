package com.siq.ecommerce.buyingservice.dto.buying;

import com.siq.ecommerce.buyingservice.model.*;
import com.siq.ecommerce.buyingservice.service.*;
import com.siq.ecommerce.productclient.dto.product.*;
import lombok.*;
import org.springframework.stereotype.*;

@Component
@AllArgsConstructor
public class BuyingDetailDtoConverter {
    private final ProductWrapperService wrapperService;

    public BuyingDetailDto convert(BuyingDetail from) {
        ProductDto productDto = wrapperService.getProductById(from.getProductId());

        return new BuyingDetailDto(
                from.getId(),
                productDto,
                from.getQuantity());
    }
}
