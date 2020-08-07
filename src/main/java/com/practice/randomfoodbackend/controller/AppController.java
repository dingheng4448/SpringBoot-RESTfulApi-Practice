package com.practice.randomfoodbackend.controller;

import com.practice.randomfoodbackend.model.Food;
import com.practice.randomfoodbackend.model.FoodQuery;
import com.practice.randomfoodbackend.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class AppController {

    private FoodRepository foodRepository;

    @Autowired
    public void AppController(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    @PostMapping(value = "/randomfood", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<> retrieveFood(@RequestBody FoodQuery foodQuery) {
        if (foodQuery.getFoodTags() == null) {
            return new ResponseEntity<>("Invalid JSON passed!", HttpStatus.BAD_REQUEST);
        }

        String[] queryTags = foodQuery.getFoodTags();
        List<Food> foodList = foodRepository.findAll();
        List<Food> resultsList = new ArrayList<>();

        for (Food food : foodList) {
            List<String> retrievedTags = Arrays.asList(food.getTags().split(","));

            for (String tag : queryTags) {
                if (retrievedTags.contains(tag)) {
                    resultsList.add(food);
                }
            }
        }

        if (resultsList.isEmpty()) {
            return new ResponseEntity<>("No food found with the given tags ):", HttpStatus.NOT_FOUND);
        }

        Random rand = new Random();
        return new ResponseEntity<>(resultsList.get(rand.nextInt(resultsList.size())), HttpStatus.OK);
    }

    @PostMapping("/save/food")
    public ResponseEntity saveFood(@RequestBody Food food) {
        if (food.getFoodName() != null && food.getTags() != null) {
            foodRepository.save(food);
            return new ResponseEntity<>("Food successfully saved to repository!", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Invalid JSON passed!", HttpStatus.BAD_REQUEST);
        }
    }
}