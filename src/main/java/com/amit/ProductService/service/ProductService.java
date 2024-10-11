package com.amit.ProductService.service;

import com.amit.ProductService.model.ProductRequest;
import com.amit.ProductService.model.ProductResponse;

public interface ProductService {

    long addProduct(ProductRequest productRequest);

    ProductResponse getProductByID(long productId);

    void reduceQuantity(long productId, long quantity);
}
