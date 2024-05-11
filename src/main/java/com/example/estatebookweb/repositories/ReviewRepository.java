package com.example.estatebookweb.repositories;

import com.example.estatebookweb.models.EstateModel;
import com.example.estatebookweb.models.ReviewModel;
import com.example.estatebookweb.models.UserModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository  extends JpaRepository<ReviewModel, Long> {
    List<ReviewModel> getReviewModelsByUserReviewed(UserModel user);
}