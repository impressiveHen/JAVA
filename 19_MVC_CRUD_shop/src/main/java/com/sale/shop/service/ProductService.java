package com.sale.shop.service;

import com.sale.shop.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class ProductService {
    @Autowired
    ProductRepository repo;

    public List<Product> listAll() {
        return repo.findAll();
    }

    public void save(Product product) {
        repo.save(product);
    }

    public Product getById(long id) {
        Optional<Product> product = repo.findById(id);
        return product.get();
    }

    public void deleteById(long id) {
        repo.deleteById(id);
    }

}
