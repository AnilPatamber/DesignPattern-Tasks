package com.ims.controller;

import com.ims.exception.ItemNotFoundException;
import com.ims.model.Product;
import com.ims.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/ims")
public class ProductController {
    @Autowired
    ProductService productService;
    @GetMapping("/products")
    public List<Product> getAllProduct(){
        List<Product> allProducts = productService.getAllProducts();
        return allProducts;

    }

    @PostMapping("/products")
    public String addAllProduct(@RequestBody List<Product> prod){
        String msg = productService.addAllProduct(prod);
        return msg;

    }
    @PostMapping("/product")
    public String addProduct(@RequestBody Product prod){
        String msg = productService.addProduct(prod);
        return msg;
    }
    @DeleteMapping("/products")
    public String deleteExpiredProducts(){
        return productService.removeExpiredItems();
    }
    @GetMapping("/sortedProducts")
    public List<Product> getProductsSortedByExpiry(){
        return productService.getAllProductsSortedByExpiry();
    }
    //Applies discount on expiring items
    @PutMapping("/applyDiscount")
    public List<Product> applyDiscount(){
        return productService.applyDiscount();
    }
    //search product for given id, if not found throws ItemNotFoundException
    @GetMapping("/product/{id}")
    public ResponseEntity<Product> searchProduct(@PathVariable Integer id){
        //  Integer pid = Integer.parseInt(id);
        Product product = productService.getProductById(id);
        if(product==null)
            throw new ItemNotFoundException();
        else
            return new ResponseEntity<>(product, HttpStatus.FOUND);

    }
}
