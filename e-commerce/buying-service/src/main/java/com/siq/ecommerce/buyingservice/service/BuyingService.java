package com.siq.ecommerce.buyingservice.service;

import com.siq.ecommerce.buyingservice.dto.buying.*;
import com.siq.ecommerce.buyingservice.dto.shipment.*;
import com.siq.ecommerce.buyingservice.enums.*;
import com.siq.ecommerce.buyingservice.model.*;
import com.siq.ecommerce.buyingservice.repository.*;
import com.siq.ecommerce.productclient.exception.*;
import lombok.*;
import org.springframework.stereotype.*;

import java.util.*;
import java.util.stream.*;

@Service
@AllArgsConstructor
public class BuyingService {

    private final ShipmentService shipmentService;
    private final BuyingDetailService buyingDetailService;

    private final BuyingDtoConverter buyingDtoConverter;
    private final BuyingRepository buyingRepository;

    public BuyingDto createBuying(CreateBuyingRequest request) {
        Shipment shipment = shipmentService.createShipment(request);

        return saveBuying(shipment);
    }

    public BuyingDto createBuyingByShipment(Long shipmentId) {
        Shipment copiedShipment = shipmentService.copyShipment(shipmentId);

        return saveBuying(copiedShipment);
    }

    public List<BuyingDto> getBuyingList() {
        return buyingRepository
                .findAll()
                .stream()
                .map(buyingDtoConverter::convert)
                .collect(Collectors.toList());
    }

    public BuyingDto getBuyingById(Long id) {
        return buyingDtoConverter.convert(findOpenBuyingById(id));
    }

    public BuyingCostDto getBuyingCostDto(Long id) {

        BuyingCostDto buyingCostDto = new BuyingCostDto(id);

        buyingCostDto.setShipment(getShipmentByBuyingId(id));

        buyingCostDto.setBuyingDetails(
                buyingDetailService.getBuyingDetailsById(id));

        buyingCostDto.setTotalCost(
                buyingCostDto.getBuyingDetails()
                        .stream()
                        .map(buyingDetailDto -> buyingDetailDto.getProduct().getPrice() * buyingDetailDto.getQuantity())
                        .mapToDouble(Double::doubleValue)
                        .sum());

        return buyingCostDto;
    }

    public void completeBuying(Long id) {
        changeBuyingStatus(id, Status.COMPLETED);
    }

    public void rejectBuying(Long id) {
        changeBuyingStatus(id, Status.REJECTED);

        buyingDetailService.returnProductsInBuyingToStock(id);
    }

    public ShipmentDto getShipmentByBuyingId(Long id) {
        return getBuyingById(id).getShipment();
    }

    public ShipmentDto updateShipment(Long id, UpdateShipmentRequest request) {
        return shipmentService.updateShipment(
                request,
                findOpenBuyingById(id).getShipment());
    }

    public Long checkIfPendingBuying(Long id) {
        if (buyingRepository.existsByIdAndStatusIs(id, Status.PENDING)){
            return id;
        }
        throw new BuyingNotFoundException("Could not find Pending Buying by id: " + id);
    }

    protected Buying findOpenBuyingById(Long id) {
        return buyingRepository.findByIdAndStatusIs(id, Status.PENDING)
                .orElseThrow( () ->
                        new BuyingNotFoundException("Could not find Pending Buying by id: " + id));

    }

    private void changeBuyingStatus(Long id, Status status) {
        Buying buying = findOpenBuyingById(id);

        buying.setStatus(status);
        buyingRepository.save(buying);
    }

    private BuyingDto saveBuying(Shipment shipment) {
        Buying buying = new Buying();

        buying.setShipment(shipment);

        return buyingDtoConverter.convert(buyingRepository.save(buying));
    }

}
