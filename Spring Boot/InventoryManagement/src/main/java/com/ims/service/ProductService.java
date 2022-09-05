package com.ims.service;

import com.ims.model.Product;
import com.ims.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    //To add single product
    public String addProduct(Product product){
        product.calculateExpiryDate();
        productRepository.save(product);
        return "product saved successfully";
    }

    //To get All products
    public List<Product> getAllProducts() {
        Iterable<Product> productIterable = productRepository.findAll();
        List<Product> allProduct = new ArrayList<>();
        productIterable.forEach(allProduct::add);
        return allProduct;
    }

    //To add multiple products
    public String addAllProduct(List<Product> prod) {
        prod.forEach(Product::calculateExpiryDate);
        productRepository.saveAll(prod);
        return "All products saved to the database";
    }

    //To get product using product id
    public Product getProductById(Integer id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElse(null);
    }
    public String removeExpiredItems(){
        Date date = new Date();
        int removedItems = productRepository.removeExpiredProducts(date);
        if(removedItems<1)
            return "No Expired item found";
        return "Expired items removed successfully: "+removedItems;
    }
    //Get sorted products list based on their expiry date
    public List<Product> getAllProductsSortedByExpiry() {
        Iterable<Product> productIterable = productRepository.findAll();
        List<Product> allProduct = new ArrayList<>();
        productIterable.forEach(allProduct::add);
        List<Product> allSortedProducts = allProduct.stream()
                .sorted((prod1,prod2)-> prod1.getExpiryDate().compareTo(prod2.getExpiryDate()))
                .collect(Collectors.toList());
        return allSortedProducts;
    }
    public List<Product> applyDiscount() {
        Iterable<Product> productIterable = productRepository.findAll();
        List<Product> productList = new ArrayList<>();
        productIterable.forEach(product -> {
            int months = (int)(product.getExpiryDate().getTime() - new Date().getTime())/1000*60*60*24*30;
            if(months<=6){
                product.setPrice(product.getPrice()*0.8);
                productRepository.save(product);
                productList.add(product);
            }
        });
        return productList;
    }

}
