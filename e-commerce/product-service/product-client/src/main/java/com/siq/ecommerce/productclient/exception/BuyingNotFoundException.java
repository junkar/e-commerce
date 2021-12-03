package com.siq.ecommerce.productclient.exception;

import org.springframework.http.*;
import org.springframework.web.server.*;

public class BuyingNotFoundException extends RuntimeException {

    public BuyingNotFoundException(String message) { super(message);}
}
