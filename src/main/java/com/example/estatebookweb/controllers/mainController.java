package com.example.estatebookweb.controllers;

import com.example.estatebookweb.models.EstateModel;
import com.example.estatebookweb.models.ReviewModel;
import com.example.estatebookweb.models.UserModel;
import com.example.estatebookweb.repositories.EstateRepository;
import com.example.estatebookweb.repositories.ReviewRepository;
import com.example.estatebookweb.repositories.UserRepository;
import com.example.estatebookweb.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class mainController {
    private final EstateRepository estateRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    private final  ReviewRepository reviewRepository;

    @GetMapping("/html")
    public String getHome(Model model, @RequestParam(required = false) String estates) {
        List<EstateModel> estateList = new ArrayList<>();


        if (estates != null && !estates.isEmpty()) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {

                estateList = objectMapper.readValue(estates, new TypeReference<List<EstateModel>>(){});
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        } else {

              estateList = estateRepository.findAll();
        }


        model.addAttribute("estates", estateList);

        return "html/mainPage";
    }
    @GetMapping("/chat_test")
    public String getChatTest() {


        return "html/chat_test";
    }
    public mainController(EstateRepository estateRepository, UserService userService, UserRepository userRepository, ReviewRepository reviewRepository) {
        this.estateRepository = estateRepository;
        this.userService = userService;
        this.userRepository = userRepository;
        this.reviewRepository = reviewRepository;
    }

    @GetMapping("/profile/{id}")
    public String getProfile(@PathVariable  Long id, Model model) {
        Optional<UserModel> currentUser = userRepository.findById(id);
        if (currentUser != null) {
            model.addAttribute("currentUser", currentUser);
            return "html/profile_page";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/addEstate")
    public String addEstate() {
        return "html/addEstate";
    }
    @GetMapping("/editEstate/{id}")
    public String editEstate(@PathVariable Long id, Model model, Map<String, Object> estateMap) {
        Optional<EstateModel> estate = estateRepository.findEstateModelById(id);
        model.addAttribute("estate", estate);
        estateMap.put("estate", estate);
        return "html/editEstate";
    }
    @GetMapping("/chat")
    public String chat() {
        return "html/chat";
    }

    @GetMapping("/estatePage/{id}")
    public String estatePage(@PathVariable Long id, Model model) {
        Optional<EstateModel> estateModel = estateRepository.findEstateModelById(id);
        UserModel user = estateModel.get().getUser();

        if (estateModel.isPresent()) {
            EstateModel estate = estateModel.get();
            model.addAttribute("estate", estate);
            model.addAttribute("user", user);
            return "html/estatePage";
        } else {
            return "redirect:/";
        }

    }

    @GetMapping("/profileEstates")
    public String profileEstates(Model model) {
        UserModel currentUser = userService.getCurrentUserLogin();
        if (currentUser != null) {
            model.addAttribute("currentUser", currentUser);
            return "html/profile_estates";
        } else {
            return "redirect:/login";
        }
    }


}