package application.backend.service;

import application.backend.model.Products;
import application.backend.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepo repo;

    public List<Products> getAllProducts() {
        return repo.findAll();
    }

    public Products getProductById(int id) {
        return repo.findById(id).isPresent() ? repo.findById(id).get() : null;
    }

    public Products addProduct(Products product, MultipartFile file) throws IOException {
        product.setImageName(file.getOriginalFilename());
        product.setImageType(file.getContentType());
        product.setImage(file.getBytes());

        return repo.save(product);
    }

    public Products updateProduct(int id, Products product, MultipartFile file) throws IOException {
        Products existingProduct = repo.findById(id).orElse(null);

        if (existingProduct != null) {
            existingProduct.setProductName(product.getProductName());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setProductCategory(product.getProductCategory());
            existingProduct.setQuantity(product.getQuantity());
            existingProduct.setAvailable(product.isAvailable());
            existingProduct.setDate(product.getDate());

            if(file != null && !file.isEmpty()) {
                existingProduct.setImage(file.getBytes());
                existingProduct.setImageName(file.getOriginalFilename());
                existingProduct.setImageType(file.getContentType());
            }

            return repo.save(existingProduct);
        }
        return null;
    }

    public void deleteProduct(int id) {
        repo.deleteById(id);
    }

    public List<Products> searchProducts(String keyword) {
        return repo.searchProducts(keyword);
    }
}
