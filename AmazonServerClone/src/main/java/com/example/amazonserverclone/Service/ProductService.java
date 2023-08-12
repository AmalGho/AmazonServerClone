package com.example.amazonserverclone.Service;

import com.example.amazonserverclone.Model.Category;
import com.example.amazonserverclone.Model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ProductService {

    ArrayList<Product> products = new ArrayList<>();
    private final CategoryService categoryService;


    public ArrayList<Product> getAllProducts() {
        return products;
    }

    public Product getProductById(Integer id) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id)
                return products.get(i);
        }
        return null;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public boolean deleteProduct(Integer id) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id){
                products.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean updateProduct(Integer id, Product product) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                products.set(i, product);
                return true;
            }
        }
        return false;
    }


//    get all products of a specific category
    public ArrayList<Product> getProductsByCategory(String categoryName) {
        ArrayList<Product> productsOfCategory = new ArrayList<>();
        Integer categoryId;
        Category category;
        for (int i = 0; i < products.size(); i++) {
            categoryId = products.get(i).getCategoryID();
            category = categoryService.getCategoryById(categoryId);
            if (category.getName().equalsIgnoreCase(categoryName)) {
                productsOfCategory.add(products.get(i));
            }
        }
        return productsOfCategory;
    }
}
