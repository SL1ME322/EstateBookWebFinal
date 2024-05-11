package com.example.estatebookweb.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Review")
public class ReviewModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Review")
    private Long id;


    //@NotBlank(message = "Location cannot be blank")
    @Column(name = "Stars")
    private int stars;

    @Column(name = "Review_Text")
    private String  review_text;
    //@NotNull(message = "Price cannot be null")

    @ManyToOne
    @JoinColumn(name = "Reviewed_User_ID")
    private UserModel userReviewed;


    @ManyToOne
    @JoinColumn(name = "Reviewing_User_ID")
    private UserModel userReviewing;


}
