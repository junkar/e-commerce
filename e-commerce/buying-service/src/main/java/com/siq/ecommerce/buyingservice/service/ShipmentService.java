package com.siq.ecommerce.buyingservice.service;

import com.siq.ecommerce.buyingservice.dto.buying.CreateBuyingRequest;
import com.siq.ecommerce.buyingservice.dto.shipment.*;
import com.siq.ecommerce.buyingservice.model.*;
import com.siq.ecommerce.buyingservice.repository.*;
import com.siq.ecommerce.productclient.exception.*;
import lombok.*;
import org.springframework.stereotype.*;

@Service
@AllArgsConstructor
public class ShipmentService {

    private final ShipmentDtoConverter shipmentDtoConverter;
    private final ShipmentRepository shipmentRepository;

    public Shipment createShipment (CreateBuyingRequest request) {
        Shipment shipment = new Shipment(request.getCityCode(), request.getLocation());

        return shipmentRepository.save(shipment);
    }

    public ShipmentDto updateShipment (UpdateShipmentRequest request, Shipment shipment) {
        shipment.setCityCode(request.getCityCode());
        shipment.setLocation(request.getLocation());

        return shipmentDtoConverter.convert(shipmentRepository.save(shipment));
    }

    protected Shipment findShipmentById (Long id) {
        return shipmentRepository.findById(id)
                .orElseThrow( () ->
                        new ShipmentNotFoundException("Shipment could not find by id: " + id));
    }

    public Shipment copyShipment(Long shipmentId) {
        Shipment oldShipment = findShipmentById(shipmentId);

        Shipment newShipment = new Shipment(oldShipment);

        return shipmentRepository.save(newShipment);
    }
}
