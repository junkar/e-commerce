package com.siq.ecommerce.buyingservice.service;

import com.siq.ecommerce.buyingservice.dto.buying.*;
import com.siq.ecommerce.buyingservice.model.*;
import com.siq.ecommerce.buyingservice.repository.*;
import lombok.*;
import org.springframework.stereotype.*;

import java.util.*;
import java.util.stream.*;

@Service
@AllArgsConstructor
public class BuyingDetailService {

    private final BuyingDetailRepository buyingDetailRepository;
    private final BuyingDetailDtoConverter buyingDetailDtoConverter;

    private final ProductWrapperService wrapperService;


    public void changeProductQuantityAtBuying(Long id, ChangeInBuyingDto changeInBuyingDto) {
        BuyingDetail buyingDetail = getBuyingDetail(id, changeInBuyingDto);

        updateBuyingDetailQuantities(
                buyingDetail,
                changeInBuyingDto.getProductId(),
                changeInBuyingDto.getQuantity());

        wrapperService.updateProductsAtStock(changeInBuyingDto.getProductId(), changeInBuyingDto.getQuantity() * (-1));
    }

    public void removeProductFromBuying(Long id, RemoveFromBuyingDto removeFromBuyingDto) {
        BuyingDetail buyingDetail = buyingDetailRepository.findByBuyingIdAndProductId(
                id,
                removeFromBuyingDto.getProductId());

        buyingDetailRepository.delete(buyingDetail);

        wrapperService.updateProductsAtStock(
                buyingDetail.getProductId(),
                buyingDetail.getQuantity());
    }


    public List<BuyingDetailDto> getBuyingDetailsById(Long buyingId) {
        return buyingDetailRepository
                .findAllByBuyingId(buyingId)
                .stream()
                .map(buyingDetailDtoConverter::convert)
                .collect(Collectors.toList());
    }

    public void returnProductsInBuyingToStock(Long id) {
        getBuyingDetailsById(id)
                .forEach(buyingDetailDto ->
                        wrapperService.updateProductsAtStock(
                                buyingDetailDto.getProduct().getId(),
                                buyingDetailDto.getQuantity()));
    }



    private BuyingDetail getBuyingDetail(Long id, ChangeInBuyingDto changeInBuyingDto) {
        BuyingDetail buyingDetail = buyingDetailRepository.findByBuyingIdAndProductId(
                id,
                changeInBuyingDto.getProductId());

        if (buyingDetail == null) {
            buyingDetail = new BuyingDetail(
                    id,
                    changeInBuyingDto.getProductId());
        }

        return buyingDetail;
    }

    private void updateBuyingDetailQuantities(BuyingDetail buyingDetail, Long productId, Integer quantity) {
        setQuantityOfBuyingDetail(buyingDetail,
                productId,
                quantity);

        buyingDetailRepository.save(buyingDetail);
    }

    private void setQuantityOfBuyingDetail(BuyingDetail buyingDetail, Long productId, Integer quantity) {
        wrapperService.checkProductQuantityIfEnough(
                productId,
                quantity);

        buyingDetail.setQuantity(buyingDetail.getQuantity() + quantity);
    }
}
