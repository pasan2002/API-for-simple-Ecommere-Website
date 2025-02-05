package application.backend.controller;

import application.backend.model.Products;
import application.backend.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class AdminController {
    @Autowired
    ProductService productService;

    @GetMapping("/admin")
    public String admin() {
        return "Welcome to Admin Controller";
    }

    @PostMapping("/admin/products")
    public ResponseEntity<?> addProduct(@RequestPart("product") String productJson,
                                        @RequestPart("file") MultipartFile file) {
        try {
            Products product = new ObjectMapper().readValue(productJson, Products.class);
            Products product1 = productService.addProduct(product, file);
            return new ResponseEntity<>(product1, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/admin/products/{id}")
    public ResponseEntity<?> updateProduct(
            @PathVariable int id,
            @RequestPart("product") String productJson,
            @RequestPart(value = "file", required = false) MultipartFile file) {
        try {
            Products existingProduct = productService.getProductById(id);
            if (existingProduct == null) {
                return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
            }

            Products productUpdate = new ObjectMapper().readValue(productJson, Products.class);

            if (file != null && !file.isEmpty()) {
                productUpdate.setImage(file.getBytes());
                productUpdate.setImageName(file.getOriginalFilename());
                productUpdate.setImageType(file.getContentType());
            } else {
                productUpdate.setImage(existingProduct.getImage());
                productUpdate.setImageName(existingProduct.getImageName());
                productUpdate.setImageType(existingProduct.getImageType());
            }

            Products updatedProduct = productService.updateProduct(id, productUpdate, file);
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("/admin/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        Products product = productService.getProductById(id);
        if (product != null) {
            productService.deleteProduct(id);
            return new ResponseEntity<>("Product deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
    }
}
