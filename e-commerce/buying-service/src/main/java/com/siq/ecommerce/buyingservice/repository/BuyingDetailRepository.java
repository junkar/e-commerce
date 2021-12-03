package com.siq.ecommerce.buyingservice.repository;

import com.siq.ecommerce.buyingservice.model.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface BuyingDetailRepository extends JpaRepository<BuyingDetail, Long> {
    List<BuyingDetail> findAllByBuyingId(Long buyingId);
    BuyingDetail findByBuyingIdAndProductId(Long buyingId, Long productId);
}
