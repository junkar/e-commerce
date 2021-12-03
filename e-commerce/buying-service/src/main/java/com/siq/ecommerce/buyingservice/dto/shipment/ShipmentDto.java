package com.siq.ecommerce.buyingservice.dto.shipment;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShipmentDto {
    private Long id;
    private String cityCode;
    private String location;
}
