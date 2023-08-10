package com.example.amazonserverclone.Service;

import com.example.amazonserverclone.Model.Merchant;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Service
public class MerchantService {

    ArrayList<Merchant> merchants = new ArrayList<>();

    public ArrayList<Merchant> getAllMerchants() {
        return merchants;
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

}
