package com.example.amazonserverclone.Service;

import com.example.amazonserverclone.Model.MerchantStock;
import com.example.amazonserverclone.Model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class MerchantStockService {

    ArrayList<MerchantStock> merchantStocks = new ArrayList<>();
    private final ProductService productService;

    public ArrayList<MerchantStock> getAllMerchantStocks() {
        return merchantStocks;
    }

    public void addMerchantStock(MerchantStock merchantStock) {
        merchantStocks.add(merchantStock);
    }

    public boolean deleteMerchantStock(Integer id) {
        for (int i = 0; i < merchantStocks.size(); i++) {
            if (merchantStocks.get(i).getId() == id) {
                merchantStocks.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean updateMerchantStock(Integer id, MerchantStock merchantStock) {
        for (int i = 0; i < merchantStocks.size(); i++) {
            if (merchantStocks.get(i).getId() == id) {
                merchantStocks.set(i, merchantStock);
                return  true;
            }
        }
        return false;
    }


//    products quantity in stock
    public String productQuantity(String productName) {
        Integer counter = 0;
        for (int i = 0; i < merchantStocks.size(); i++) {
            Integer productId = merchantStocks.get(i).getProductId();
            Product product = productService.getProductById(productId);
            if ( product.getName().equalsIgnoreCase(productName)) {
                counter++;
            }
        }
        return "there are " + counter + " of the " + productName + " product";
    }

}
