package com.example.amazonserverclone.Service;

import com.example.amazonserverclone.Model.Merchant;
import com.example.amazonserverclone.Model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class MerchantService {

    ArrayList<Merchant> merchants = new ArrayList<>();
    private final MerchantStockService merchantStockService;

    public ArrayList<Merchant> getAllMerchants() {
        return merchants;
    }

    public Merchant getMerchantById(Integer id) {
        for (int i = 0; i < merchants.size(); i++) {
            if (merchants.get(i).getId() == id)
                return merchants.get(i);
        }
        return null;
    }

    public void addMerchant(Merchant merchant) {
        merchants.add(merchant);
    }

    public boolean deleteMerchant(Integer id) {
        for (int i = 0; i < merchants.size(); i++) {
            if (merchants.get(i).getId() == id) {
                merchants.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean updateMerchant(Integer id, Merchant merchant) {
        for (int i = 0; i < merchants.size(); i++) {
            if (merchants.get(i).getId() == id) {
                merchants.set(i, merchant);
                return true;
            }
        }
        return false;
    }

    public boolean addStock(Integer productId, Integer merchantId, Integer amount) {

        for (int i = 0; i < merchantStockService.merchantStocks.size(); i++) {
            if (merchantStockService.merchantStocks.get(i).getProductId() == productId
                    && merchantStockService.merchantStocks.get(i).getMerchantId() == merchantId) {
                merchantStockService.merchantStocks.get(i).setStock(merchantStockService.merchantStocks.get(i).getStock() + amount);
                return true;
            }
        }
        return false;
    }

}
