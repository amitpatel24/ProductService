package com.amit.ProductService.controller;
import com.amit.ProductService.model.ProductRequest;
import com.amit.ProductService.model.ProductResponse;
import com.amit.ProductService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    @PostMapping
    public ResponseEntity<Long> addProduct(@RequestBody ProductRequest productRequest){
        long productId = productService.addProduct(productRequest);
        return new ResponseEntity<>(productId, HttpStatus.CREATED);

    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductByID(@PathVariable("id")long productId){
        ProductResponse productResponse = productService.getProductByID(productId);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @PutMapping("/{reduceQuantity}/{id}")
    public ResponseEntity<Void> reduceQuantity(@PathVariable("id")long productId, @RequestParam("quantity")long quantity) {
        productService.reduceQuantity(productId, quantity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    }






