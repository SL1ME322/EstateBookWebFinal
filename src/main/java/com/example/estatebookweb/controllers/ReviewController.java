package com.example.estatebookweb.controllers;

import com.example.estatebookweb.models.EstateModel;
import com.example.estatebookweb.models.ReviewModel;
import com.example.estatebookweb.models.UserModel;
import com.example.estatebookweb.repositories.ReviewRepository;
import com.example.estatebookweb.repositories.UserRepository;
import com.example.estatebookweb.services.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/review")
public class ReviewController {
    private final ReviewRepository reviewRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    public ReviewController(ReviewRepository reviewRepository , UserService userService, UserRepository userRepository ) {
        this.reviewRepository = reviewRepository;

        this.userService = userService;
        this.userRepository = userRepository;
    }
    @GetMapping("/addReview")
    public String addReview(Model model, @RequestParam Long id) {
        UserModel currentUser = userService.getCurrentUserLogin();
        UserModel reviewedUser = userRepository.getById(id);

        if (currentUser != null) {
            model.addAttribute("currentUser", currentUser);
        }
        if (reviewedUser != null) {
            model.addAttribute("reviewedUser", reviewedUser);
        }
        model.addAttribute("currentUser");
        model.addAttribute("reviewedUser");
        return "html/addReview";
    }
    @RequestMapping("/createReview")
    public ResponseEntity<String> createReview(@RequestBody ReviewModel reviewModel) {
        reviewRepository.save(reviewModel);
        return ResponseEntity.status(HttpStatus.OK).body("Отзыв отправлен!");
    }
    @GetMapping("/reviewsPage/{id}")
    public String reviews_page(@PathVariable  Long id, Model model) {
        UserModel currentUser = userService.getCurrentUserLogin();
        List<ReviewModel> reviews = reviewRepository.getReviewModelsByUserReviewed(currentUser);
        int reviewCount = reviews.size();
        model.addAttribute("reviewCount", reviewCount);
        model.addAttribute("reviews", reviews);
        model.addAttribute("currentUser", currentUser);
        return "html/reviews_page";
    }

}
