package com.sivakg.practice.notebook.services;

import com.sivakg.practice.notebook.entities.User;
import com.sivakg.practice.notebook.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    //@Autowired
    //private PasswordEncoder passwordEncoder;

    public User create(User user){
        return userRepository.save(user);
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public User getByEmail(String email){
        Optional<User>  user=userRepository.findByEmail(email);
        return user.orElse(null);
    }

    public User getById(int id){
        Optional<User>  user=userRepository.findById(id);
        return user.orElse(null);
    }

    public User update(int id,User user){
        Optional<User>  oldUser=userRepository.findById(user.getId());
        if(oldUser.isPresent() && oldUser.get().getId()==id){
            userRepository.save(user);
            return  user;
        }else{
            return null;
        }

    }
    public void deleteById(int id){
        userRepository.deleteById(id);
    }

    public boolean authenticate(String email, String rawPassword) {
        Optional<User> optionalUser= userRepository.findByEmail(email);
        //return optionalUser.filter(user -> passwordEncoder.matches(rawPassword, user.getPassword())).isPresent();

        return optionalUser.filter(user -> user.getPassword().equals(rawPassword)).isPresent();
    }



}
