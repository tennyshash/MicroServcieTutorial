package com.hotelrating.userservice.Services.Implementation;

import com.hotelrating.userservice.Entity.User;
import com.hotelrating.userservice.Exceptions.ResourceNotFoundException;
import com.hotelrating.userservice.Repository.UserRepository;
import com.hotelrating.userservice.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {

        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByID(String userID)  {
        User user;
        user=  userRepository.findById(userID).orElseThrow(( ()-> new ResourceNotFoundException("Resource Not  Found with ID" + userID)));
        return user;
    }
}
