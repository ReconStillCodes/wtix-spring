package com.wtix.wtix.service;

import com.wtix.wtix.model.User;
import com.wtix.wtix.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public boolean validateLogin(String email, String password){
        User user = userRepository.findByEmailAndPassword(email, password);
        if(user == null){

            return false;
        }
        return true;
    }

    public User getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public boolean validateUniqueEmail(String email){
        User user = getUserByEmail(email);
        if(user == null){
            return true;
        }
        return false;
    }

    public void createUser(String name, String email, String password){
        User user = new User(name, email, password);
        System.out.println("Test");
        userRepository.save(user);
    }

    public User getUserById(Integer id){
        Optional<User> user = userRepository.findById(id);
        User u = null;
        if(user.isPresent()){
            u = user.get();
        }

        return u;
    }
}
