package com.siq.ecommerce.buyingservice.dto.buying;

import com.siq.ecommerce.buyingservice.dto.shipment.*;
import com.siq.ecommerce.buyingservice.model.*;
import lombok.*;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
public class BuyingCostDto {
    private Long id;
    private List<BuyingDetailDto> buyingDetails;
    private Double totalCost;
    private ShipmentDto shipment;

    public BuyingCostDto(Long id) {
        this.id = id;
    }
}
