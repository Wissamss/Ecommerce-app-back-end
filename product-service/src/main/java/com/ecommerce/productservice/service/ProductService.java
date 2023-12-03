package com.ecommerce.productservice.service;

import com.ecommerce.productservice.model.Product;
import com.ecommerce.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Base64;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"+id));
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public Product  saveProduct(MultipartFile file,String designation, Integer quantity, BigDecimal price, String category)
    {
        Product p = new Product();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if(fileName.contains(".."))
        {
            System.out.println("not a a valid file");
        }
        try {
            p.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        p.setDesignation(designation);
        p.setCategory(category);
        p.setPrice(price);
        p.setQuantity(quantity);
        return productRepository.save(p);
    }
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Product updateProduct(Long id, Product product){
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
