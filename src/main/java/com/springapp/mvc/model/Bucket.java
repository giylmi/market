package com.springapp.mvc.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by adel on 28.05.14.
 */
public class Bucket {

    private Set<Product> products = new HashSet<>();

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public boolean isInBucket(Product product){
        return products.contains(product);
    }

    public void add(Product product){
        products.add(product);
    }

    public void delete(Product product){
        for (Iterator<Product> t = products.iterator(); t.hasNext(); )
            if (product.equals(t.next())) {
                t.remove();
                return;
            }
    }
}
