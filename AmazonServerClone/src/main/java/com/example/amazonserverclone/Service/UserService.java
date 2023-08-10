package com.example.amazonserverclone.Service;

import com.example.amazonserverclone.Model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {

    ArrayList<User> users = new ArrayList<>();

    public ArrayList<User> getAllUsers() {
        return users;
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

}
