package com.siq.ecommerce.productserver.repository;

import com.siq.ecommerce.productserver.model.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);

}
