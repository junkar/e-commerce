package com.siq.ecommerce.buyingservice.dto.shipment;

import com.siq.ecommerce.buyingservice.model.*;
import lombok.*;
import org.springframework.stereotype.*;

@Component
@AllArgsConstructor
public class ShipmentDtoConverter {

    public ShipmentDto convert(Shipment from) {
        return new ShipmentDto(
                from.getId(),
                from.getCityCode(),
                from.getLocation());
    }
}
