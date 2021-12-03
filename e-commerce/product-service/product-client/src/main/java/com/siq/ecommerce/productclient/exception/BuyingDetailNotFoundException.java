package com.siq.ecommerce.productclient.exception;

import org.springframework.http.*;
import org.springframework.web.server.*;

public class BuyingDetailNotFoundException extends RuntimeException {

    public BuyingDetailNotFoundException(String message) { super(message);}
}
