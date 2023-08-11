package com.example.amazonserverclone.Service;

import com.example.amazonserverclone.Model.Merchant;
import com.example.amazonserverclone.Model.Product;
import com.example.amazonserverclone.Model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserService {

    ArrayList<User> users = new ArrayList<>();
    private final ProductService productService;
    private final MerchantService merchantService;
    private final MerchantStockService merchantStockService;


    public ArrayList<User> getAllUsers() {
        return users;
    }

    public User getUserById(Integer id) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == id)
                return users.get(i);
        }
        return null;
    }

    public void addUsers(User user) {
        users.add(user);
    }

    public boolean deleteUser(Integer id) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == id){
                users.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean updateUser(Integer id, User user) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == id) {
                users.set(i, user);
                return true;
            }
        }
        return false;
    }


    public String buyProduct(Integer userId, Integer productId, Integer merchantId) {

        User user = getUserById(userId);
        Product product = productService.getProductById(productId);
        Merchant merchant = merchantService.getMerchantById(merchantId);

        if (user == null) return "user not valid !!";
        else if (product == null) return "product not valid !!";
        else if (merchant == null) return  "merchant not valid !!";

        for (int i = 0; i < merchantStockService.merchantStocks.size(); i++) {
            if (merchantStockService.merchantStocks.get(i).getMerchantId() == merchant.getId()) {
                if (merchantStockService.merchantStocks.get(i).getProductId() == product.getId()) {
                    merchantStockService.merchantStocks.get(i).setStock(merchantStockService.merchantStocks.get(i).getStock() - 1);
                    if (user.getBalance() > product.getPrice()){
                        user.setBalance(user.getBalance() - product.getPrice());
                        return "User Buy Product Successfully";
                    }else return "you don't have enough money!!";
                }
            }
        }

        return "product not exist in merchant stock!";

    }

}
