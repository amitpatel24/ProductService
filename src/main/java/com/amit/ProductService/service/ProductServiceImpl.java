package com.amit.ProductService.service;

import com.amit.ProductService.entity.Product;
import com.amit.ProductService.exception.ProductServiceCustomException;
import com.amit.ProductService.model.ProductRequest;
import com.amit.ProductService.model.ProductResponse;
import com.amit.ProductService.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService {
     @Autowired
    private ProductRepository productRepository;

    @Override
    public long addProduct(ProductRequest productRequest) {
        log.info("Adding Product..");
        Product product = Product.builder()
                .productName(productRequest.getName())
                        .quantity(productRequest.getQuantity())
                                .price(productRequest.getPrice())
                                        .build();
        productRepository.save(product);
        log.info("Product Added");
        return product.getProductId();
    }

    @Override
    public ProductResponse getProductByID(long productId) {
        log.info("Fetching Product By ID");
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductServiceCustomException("Product with given id not found", "PRODUCT_NOT_FOUND"));

        ProductResponse productResponse = new ProductResponse();
        BeanUtils.copyProperties(product, productResponse);
        return productResponse;
    }

    @Override
    public void reduceQuantity(long productId, long quantity) {
        log.info("Reducing Quantity {}", productId, quantity);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductServiceCustomException("Product with given id not found", "PRODUCT_NOT_FOUND"));
        if (product.getQuantity() < quantity) {
            throw new ProductServiceCustomException("Quantity not available", "QUANTITY_NOT_AVAILABLE");
        }
        product.setQuantity(product.getQuantity() - quantity);
        productRepository.save(product);
        log.info("Quantity Reduced");
    }
}
