package com.specification.controller;

import com.specification.dtos.ProductCatAndPriceDto;
import com.specification.entities.Product;
import com.specification.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public ResponseEntity<List<Product>> findAllProducts(){
        return ResponseEntity.ok(productService.getProducts());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(
            @PathVariable Integer id
    ) throws Exception {
        return ResponseEntity.ok(this.productService.getProduct(id));
    }

    @PostMapping("/")
    public ResponseEntity<Product> addProduct(
            @RequestBody Product product
    ) throws Exception {
        return ResponseEntity.ok(this.productService.create(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable Integer id,
            @RequestBody Product product
    ) throws Exception {
        return ResponseEntity.ok(this.productService.updateProduct(id, product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(
            @PathVariable Integer id
    ) throws Exception {
        Product product = this.productService.getProduct(id);
        this.productService.delete(product);
        return ResponseEntity.ok("Product deleted");
    }

    @PostMapping("/compare")
    private ResponseEntity<List<Product>> compareProductWithCatAndPrice(@RequestBody ProductCatAndPriceDto dto){
        return ResponseEntity.ok(this.productService.compareProductWithPriceAndCategory(dto));
    }
}
