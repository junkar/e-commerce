package com.siq.ecommerce.buyingservice.dto.shipment;

import lombok.*;

@Value
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class UpdateShipmentRequest {
    String cityCode;
    String location;
}
