package by.academy.it.travelcompany.service;

import by.academy.it.travelcompany.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    private final List<Product> products;

    public ProductServiceImpl(){
        products = new ArrayList<>();
        products.add(new Product(1L,"Product name 1",10.0));
        products.add(new Product(2L,"Product name 2",30.0));
    }

    @Override
    public List<Product> getAllProducts() {
        return products;
    }
}
