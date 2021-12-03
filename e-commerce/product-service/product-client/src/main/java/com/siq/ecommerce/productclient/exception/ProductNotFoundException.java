package com.siq.ecommerce.productclient.exception;

import org.springframework.http.*;
import org.springframework.web.server.*;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(String message) { super(message);}
}
