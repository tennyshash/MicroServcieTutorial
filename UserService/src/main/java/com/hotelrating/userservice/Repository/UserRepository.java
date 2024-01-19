package com.hotelrating.userservice.Repository;

import com.hotelrating.userservice.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String > {

    User save(User user);

    List<User> findAll();

    @Override
    Optional<User> findById(String userID);
}
