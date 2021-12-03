package com.siq.ecommerce.buyingservice.dto.buying;

import com.siq.ecommerce.buyingservice.dto.shipment.*;
import com.siq.ecommerce.buyingservice.model.*;
import lombok.*;
import org.springframework.stereotype.*;

@Component
@AllArgsConstructor
public class BuyingDtoConverter {

    private final ShipmentDtoConverter shipmentDtoConverter;

    public BuyingDto convert (Buying from) {
        return new BuyingDto(
                from.getId(),
                from.getCreatedDate(),
                shipmentDtoConverter.convert(from.getShipment()),
                from.getStatus());
    }
}
