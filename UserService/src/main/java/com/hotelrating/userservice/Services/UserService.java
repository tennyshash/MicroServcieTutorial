package com.hotelrating.userservice.Services;

import com.hotelrating.userservice.Entity.User;

import java.util.List;


public interface UserService {
    User save(User user);

    List<User> getAllUser();
    User getUserByID(String UserID) ;

    //TODO: Update User, Delete User
}
