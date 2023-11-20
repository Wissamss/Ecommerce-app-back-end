package com.ecommerce.productservice.service;
import com.ecommerce.productservice.model.Product;
import com.ecommerce.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Integer id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product with {id} not found"+id));
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }

    public Product updateProduct(Integer id, Product product){
        Product existingProduct = getProductById(id);
        existingProduct.setDesignation(product.getDesignation());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setQuantity(product.getQuantity());
        existingProduct.setCategory(product.getCategory());
        return productRepository.save(existingProduct);
    }

    public List<Product> getProductsByCategory(String category) {
        List<Product> products= getAllProducts();
        return products.stream()
                .filter(product -> product.getCategory().equals(category))
                .toList();
    }

    public List<Product> getProductsByDesignation(String designation){
        List<Product> products= getAllProducts();
        return products.stream()
                .filter(product -> product.getDesignation().toLowerCase().contains(designation.toLowerCase()))
                .toList();
    }
}
