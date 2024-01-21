package com.hotelrating.userservice;

import com.hotelrating.userservice.Entity.Rating;
import com.hotelrating.userservice.FeignClientServices.RatingServiceFeign;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@SpringBootTest
class UserServiceApplicationTests {

    @Autowired
    private RatingServiceFeign ratingServiceFeign;
    private Logger logger= LoggerFactory.getLogger(UserServiceApplicationTests.class);

    @Test
    void contextLoads() {
    }

    @Test
    public void createRating(){
        Rating rating= Rating.builder().userID("Test").hotelID("Test").rating(10).remark("Testing ").build();
        ResponseEntity<Rating> ratingResponseEntity = ratingServiceFeign.createRating(rating);
        logger.info(" Rating  : {}", ratingResponseEntity.getBody() );
        logger.info("Rating Created from User Microservice");
    }

}
