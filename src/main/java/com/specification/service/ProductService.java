package com.specification.service;

import com.specification.dtos.ProductCatAndPriceDto;
import com.specification.entities.Product;
import com.specification.entities.User;
import com.specification.repo.ProductRepo;
import com.specification.repo.ProductSpecification;
import com.specification.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.jpa.domain.Specification.where;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private UserRepo userRepo;


    public Product getProduct(int id) throws Exception {
        return this.productRepo.findById(id).orElseThrow(() -> new Exception("product not found"));
    }

    public List<Product> getProducts() {
        return this.productRepo.findAll();
    }

    public Product create(Product product) {
        return this.productRepo.save(product);
    }

    public Product updateProduct(int id, Product product) throws Exception {
        Product product1 = getProduct(id);
        if (product.getPrice() != 0) product1.setPrice(product.getPrice());
        if (product.getCategory() != null) product1.setCategory(product.getCategory());
        if (product.getP_name() != null) product1.setP_name(product.getP_name());
        if (product.getUser() != null) this.addUser(product.getUser().getId(), product1);
        return this.productRepo.save(product1);
    }

    public void delete(Product product) throws Exception {
        Product product1 = this.getProduct(product.getP_id());
        this.productRepo.delete(product1);
    }

    public List<Product> compareProductWithPriceAndCategory(ProductCatAndPriceDto dto) {
        ProductSpecification<Product> spec = new ProductSpecification<>(dto.getCategory(), dto.getPrice());
        return productRepo.findAll(where(spec.productWithCategory()).and(spec.productGreaterThan()).and(spec.sortByAsc()));
    }

    private Product addUser(int userId, Product product) throws Exception {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new Exception("User not found with id"));
        product.setUser(user);
        return product;
    }
}
