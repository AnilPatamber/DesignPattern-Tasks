package com.ims.repository;

import com.ims.model.Product;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
    @Transactional
    @Modifying
    @Query("delete from Product p where p.id = ?1")
    int removeProductsById(Integer id);

    @Transactional
    @Modifying
    @Query("delete from Product p where p.expiryDate<?1")
    int removeExpiredProducts(Date date);

    @Transactional
    @Modifying
    @Query("delete from Product p where p.expiryDate-?1<6")
    Iterable<Product> getNearByExpiryProducts(Date date);
}
