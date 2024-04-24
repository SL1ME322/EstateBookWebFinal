package com.example.estatebookweb.controllers;

import com.example.estatebookweb.models.EstateModel;
import com.example.estatebookweb.models.UserModel;
import com.example.estatebookweb.repositories.EstateRepository;
import com.example.estatebookweb.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class mainController {
    private final EstateRepository estateRepository;
    private final UserService userService;

    @GetMapping("/html")
    public String getHome(Model model) {
        List<EstateModel> estates = estateRepository.findAll();
        model.addAttribute("estates", estates);

        return "html/mainPage";
    }
    @GetMapping("/chat_test")
    public String getChatTest() {


        return "html/chat_test";
    }
    public mainController(EstateRepository estateRepository, UserService userService) {
        this.estateRepository = estateRepository;
        this.userService = userService;
    }

    @GetMapping("/profile")
    public String getProfile(Model model) {
        UserModel currentUser = userService.getCurrentUserLogin();
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
        if (estateModel.isPresent()) {
            EstateModel estate = estateModel.get();
            model.addAttribute("estate", estate);
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

    @GetMapping("/reviewsPage")
    public String reviews_page(Model model) {
        UserModel currentUser = userService.getCurrentUserLogin();
        if (currentUser != null) {
            model.addAttribute("currentUser", currentUser);
            return "html/reviews_page";
        } else {
            return "redirect:/login";
        }

    }
}