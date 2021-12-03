package com.siq.ecommerce.buyingservice.repository;

import com.siq.ecommerce.buyingservice.enums.*;
import com.siq.ecommerce.buyingservice.model.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface BuyingRepository extends JpaRepository<Buying, Long> {
    Optional<Buying> findByIdAndStatusIs(Long id, Status status);
    boolean existsByIdAndStatusIs(Long id, Status status);
}
