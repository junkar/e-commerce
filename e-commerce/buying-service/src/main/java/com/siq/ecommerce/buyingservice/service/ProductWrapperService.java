package com.siq.ecommerce.buyingservice.service;

import com.fasterxml.jackson.databind.*;
import com.siq.ecommerce.productclient.dto.product.*;
import com.siq.ecommerce.productclient.exception.*;
import lombok.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.reactive.function.*;
import org.springframework.web.reactive.function.client.*;
import reactor.core.publisher.*;

@Service
public class ProductWrapperService {

    private final WebClient webClient;

    private final static ObjectMapper mapper = new ObjectMapper()
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

    public ProductWrapperService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:9291").build();
    }

    public ProductDto getProductById(Long id) {
        return webClient
                .get()
                .uri(uriBuilder ->
                        uriBuilder.path("/v1/product/{id}")
                                .build(id))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(ProductDto.class)
                .block();
    }

    public void checkProductQuantityIfEnough(Long productId, Integer quantity) {
        ProductDto productDto = getProductById(productId);

        if (productDto.getCount() < quantity) {
            throw new NotEnoughProductException("Not enough Products:"
                    +productDto.getName()+" in stock");
        }
    }

    public void updateProductsAtStock(Long productId, Integer quantity) {
        val body = "{\n" + "\"change\":" + quantity + "\n" +"}";

        webClient
                .put()
                .uri(uriBuilder ->
                        uriBuilder.path("/v1/product/stock/{id}")
                                .build(productId))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(body))
                .retrieve()
                .onStatus(HttpStatus::isError, response -> response.bodyToMono(String.class)
                        .flatMap(error -> Mono.error(new RuntimeException(error))))
                .bodyToMono(ProductDto.class)
                .block();
    }

}
