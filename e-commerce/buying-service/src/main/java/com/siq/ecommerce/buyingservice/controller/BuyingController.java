package com.siq.ecommerce.buyingservice.controller;

import com.siq.ecommerce.buyingservice.dto.buying.*;
import com.siq.ecommerce.buyingservice.dto.shipment.*;
import com.siq.ecommerce.buyingservice.service.*;
import lombok.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/v1/buying")
@AllArgsConstructor
public class BuyingController {
    private final BuyingService buyingService;
    private final BuyingDetailService buyingDetailService;

    @PostMapping
    public ResponseEntity<BuyingDto> createBuying (@RequestBody CreateBuyingRequest request) {
        return ResponseEntity.ok(buyingService.createBuying(request));
    }

    @PostMapping("/shipment/{shipmentId}")
    public ResponseEntity<BuyingDto> createBuyingByShipment (@PathVariable("shipmentId") Long shipmentId) {
        return ResponseEntity.ok(buyingService.createBuyingByShipment(shipmentId));
    }

    @GetMapping
    public ResponseEntity<List<BuyingDto>> getBuyingList() {
        return ResponseEntity.ok(buyingService.getBuyingList());
    }



    @GetMapping("/{id}")
    public ResponseEntity<BuyingCostDto> getBuyingDetailsWithTotalCost(@PathVariable("id") Long id) {
        return ResponseEntity.ok(buyingService.getBuyingCostDto(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BuyingCostDto> changeProductQuantityAtBuying (
            @PathVariable("id") Long id,
            @RequestBody ChangeInBuyingDto request) {

        buyingDetailService.changeProductQuantityAtBuying(buyingService.checkIfPendingBuying(id), request);

        return ResponseEntity.ok(buyingService.getBuyingCostDto(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BuyingCostDto> removeProductFromBuying (
            @PathVariable("id") Long id,
            @RequestBody RemoveFromBuyingDto request) {

        buyingDetailService.removeProductFromBuying(buyingService.checkIfPendingBuying(id), request);

        return ResponseEntity.ok(buyingService.getBuyingCostDto(id));
    }



    @PutMapping("/status/{id}")
    public ResponseEntity<BuyingCostDto> completeBuying (@PathVariable("id") Long id) {

        buyingService.completeBuying(buyingService.checkIfPendingBuying(id));

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/status/{id}")
    public ResponseEntity<BuyingCostDto> rejectBuying (@PathVariable("id") Long id) {

        buyingService.rejectBuying(buyingService.checkIfPendingBuying(id));

        return ResponseEntity.ok().build();
    }



    @PutMapping("/shipment/{id}")
    public ResponseEntity<ShipmentDto> updateShipment (
            @PathVariable("id") Long id,
            @RequestBody UpdateShipmentRequest request) {
        return ResponseEntity.ok(buyingService.updateShipment(id, request));
    }

    @GetMapping("/shipment/{id}")
    public ResponseEntity<ShipmentDto> getShipmentByBuyingId (@PathVariable("id") Long id) {
        return ResponseEntity.ok(buyingService.getShipmentByBuyingId(id));
    }
}
