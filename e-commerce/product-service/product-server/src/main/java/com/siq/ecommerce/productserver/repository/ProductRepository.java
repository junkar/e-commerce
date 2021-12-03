package com.siq.ecommerce.productserver.repository;

import com.siq.ecommerce.productserver.model.*;
import org.springframework.data.jpa.repository.*;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
