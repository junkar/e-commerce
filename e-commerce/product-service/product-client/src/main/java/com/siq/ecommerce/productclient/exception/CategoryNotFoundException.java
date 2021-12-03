package com.siq.ecommerce.productclient.exception;

import org.springframework.http.*;
import org.springframework.web.server.*;

public class CategoryNotFoundException extends RuntimeException {

    public CategoryNotFoundException(String message) { super(message);}
}
