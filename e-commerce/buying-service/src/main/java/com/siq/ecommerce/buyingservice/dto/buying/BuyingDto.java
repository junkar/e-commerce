package com.siq.ecommerce.buyingservice.dto.buying;

import com.siq.ecommerce.buyingservice.dto.shipment.*;
import com.siq.ecommerce.buyingservice.enums.*;
import lombok.*;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BuyingDto {

    private Long id;
    private Date createdDate;
    private ShipmentDto shipment;
    private Status status;
}
