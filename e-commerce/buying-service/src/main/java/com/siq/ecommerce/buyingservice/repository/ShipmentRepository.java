package com.siq.ecommerce.buyingservice.repository;

import com.siq.ecommerce.buyingservice.model.*;
import org.springframework.data.jpa.repository.*;

public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
}
