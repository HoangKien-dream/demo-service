package com.example.demo.product;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "api/v1/products")
public class ProductApi {

    private static ArrayList<Product> listProduct;
    private static Logger LOGGER = Logger.getLogger(ProductApi.class.getName());

    static {
        listProduct = new ArrayList<>();
        listProduct.add(new Product(1, "Product 01", "http://vnanh.png", 1000, 1));
        listProduct.add(new Product(2, "Product 02", "http://vnanh.png", 2000, 1));
        listProduct.add(new Product(3, "Product 03", "http://vnanh.png", 3000, 1));
        listProduct.add(new Product(4, "Product 04", "http://vnanh.png", 4000, 1));
        listProduct.add(new Product(5, "Product 05", "http://vnanh.png", 1000, 1));
        listProduct.add(new Product(6, "Product 06", "http://vnanh.png", 2000, 1));
    }

    @RequestMapping(method = RequestMethod.GET)
    public ArrayList<Product> findAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "1") int limit){
        LOGGER.log(Level.INFO, String.valueOf(page));
        LOGGER.log(Level.INFO, String.valueOf(limit));
        return listProduct;
    }

    @RequestMapping(method = RequestMethod.GET, path = "{id}")
    public Product findById(@PathVariable int id){
        Product found = null;
        for (Product product :
                listProduct) {
            if(product.getId() == id){
                found = product;
                break;
            }
        }
        return found;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Product save(@RequestBody Product product){
        listProduct.add(product);
        return product;
    }

    @RequestMapping(method = RequestMethod.PUT, path = "{id}")
    public Product update(@PathVariable int id, @RequestBody Product updateProduct){
        for (Product product :
                listProduct) {
            if(product.getId() == id){
                product.setName(updateProduct.getName());
                product.setPrice(updateProduct.getPrice());
                product.setThumbnail(updateProduct.getThumbnail());
                break;
            }
        }
        return updateProduct;
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "{id}")
    public String deleteById(@PathVariable int id){
        int foundIndex = -1;
        for (int i = 0; i < listProduct.size(); i++) {
            if(listProduct.get(i).getId() == id){
                foundIndex = i;
                break;
            }
        }
        if(foundIndex != -1){
            listProduct.remove(foundIndex);
        }
        return "Delete success";
    }
}
