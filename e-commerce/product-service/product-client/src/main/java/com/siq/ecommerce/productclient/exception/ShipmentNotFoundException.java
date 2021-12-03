package com.siq.ecommerce.productclient.exception;

import org.springframework.http.*;
import org.springframework.web.server.*;

public class ShipmentNotFoundException extends RuntimeException {

    public ShipmentNotFoundException(String message) { super(message);}
}
