package com.example.estatebookweb.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
    @Entity
    @Table(name = "Estate")
    public class EstateModel {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "ID_Estate")
        private Long id;

        //@NotBlank(message = "Ad Name cannot be blank")
        @Column(name = "Ad_Name",  columnDefinition = "NVARCHAR(255)")
        private String adName;

        //@NotBlank(message = "Location cannot be blank")
        @Column(name = "Location",  columnDefinition = "NVARCHAR(255)")
        private String location;

    //@NotNull(message = "Price cannot be null")
    @Column(name = "Price")
    private int price;

    //@NotNull(message = "Price for Month cannot be null")
    @Column(name = "Price_For_Month")
    private int priceForMonth;

    //@NotNull(message = "Mortgage Price cannot be null")
    @Column(name = "Mortgage_Price")
    private int mortgagePrice;

    //@NotNull(message = "Area cannot be null")
    @Column(name = "Area")
    private int area;

    //@NotNull(message = "House Area cannot be null")
    @Column(name = "House_Area")
    private int houseArea;

   //@NotBlank(message = "Metro Station cannot be blank")
    @Column(name = "Metro_Station", columnDefinition = "NVARCHAR(255)")
    private String metroStation;

    //@NotBlank(message = "Train Station cannot be blank")
    @Column(name = "Train_Station")
    private String trainStation;

    //@NotBlank(message = "Description cannot be blank")
    @Column(name = "Description", columnDefinition = "NVARCHAR(255)")
    private String description;

    //@NotNull(message = "Ad Date cannot be null")
    @Column(name = "Ad_Date")
    private Date adDate;

   //@NotNull(message = "Building Date cannot be null")
    @Column(name = "Building_Date")
    private Date buildingDate;

    //@NotBlank(message = "Status cannot be blank")
    @Column(name = "Status",  columnDefinition = "NVARCHAR(255)")
    private String status;

    //@NotNull(message = "Estate Rating cannot be null")
    @Column(name = "Estate_Rating")
    private Integer estateRating;

    @Column(name = "Estate_Type", columnDefinition = "NVARCHAR(255)")
    private String estateType;


    @ManyToOne
    @JoinColumn(name = "User_ID")
    private UserModel user;

    @Column(name = "Renovation", columnDefinition = "NVARCHAR(255)")
    private String renovation;

    @Column(name = "Room_Amount")
    private Integer roomAmount;

    @Column(name = "Purpose")
    private String purpose;

    @Column(name = "Possible_Purpose", columnDefinition = "NVARCHAR(255)")
    private String possiblePurpose;

    @Column(name = "Building_Type", columnDefinition = "NVARCHAR(255)")
    private String buildingType;

    @Column(name = "Adress",columnDefinition = "NVARCHAR(255)")
    private String address;

    @Column(name = "Floor_Amount")
    private Integer floorAmount;

    @Column(name = "Floor")
    private Integer floor;

    @Column(name = "Entrance")
    private String entrance;
    @Column(name = "City", columnDefinition = "NVARCHAR(255)")
    private String city;

    @ElementCollection
    @CollectionTable(name ="Estate_Files", joinColumns =@JoinColumn(name = "estate_id"))
    @Column(name = "file_content", columnDefinition = "TEXT")
    private Set<String> files;


    @JsonIgnore
    @ElementCollection
    @CollectionTable(name ="Estate_Files", joinColumns =@JoinColumn(name = "estate_id"))
    @Column(name = "threeDModel", columnDefinition = "TEXT")
    private Set<String> threeDModel;


}