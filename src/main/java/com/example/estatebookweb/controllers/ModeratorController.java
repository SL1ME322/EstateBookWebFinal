package com.example.estatebookweb.controllers;



import com.example.estatebookweb.models.ComplaintModel;
import com.example.estatebookweb.models.EstateModel;
import com.example.estatebookweb.models.UserModel;
import com.example.estatebookweb.repositories.ComplaintRepository;
import com.example.estatebookweb.repositories.EstateRepository;
import com.example.estatebookweb.repositories.UserRepository;
import com.example.estatebookweb.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/moderator")
public class ModeratorController   {
    private final EstateRepository estateRepository;

    private final UserService userService;
    private final UserRepository userRepository;
    private final ComplaintRepository complaintRepository;
    @GetMapping("/moderPage")
    public String getHome(Model model) {
        List<EstateModel> estates = estateRepository.findAll();
        model.addAttribute("estates", estates);
        List<ComplaintModel> complaints = complaintRepository.findAll();
        model.addAttribute("complaints", complaints);
        return "html/moderatorMainPage";
    }

    public ModeratorController(EstateRepository estateRepository, UserService userService, UserRepository userRepository, ComplaintRepository complaintRepository) {
        this.estateRepository = estateRepository;
        this.userService = userService;
        this.userRepository = userRepository;
        this.complaintRepository = complaintRepository;
    }




    @GetMapping("/editEstate/{id}")
    public String editEstate(@PathVariable Long id, Model model, Map<String, Object> estateMap) {
        Optional<EstateModel> estate = estateRepository.findEstateModelById(id);
        model.addAttribute("estate", estate);
        estateMap.put("estate", estate);
        return "html/editEstate";
    }


    @GetMapping("/moderEstatePage/{id}")
    public String moderEstatePage(@PathVariable Long id, Model model) {
        Optional<EstateModel> estateModel = estateRepository.findEstateModelById(id);
        if (estateModel.isPresent()) {
            EstateModel estate = estateModel.get();
            model.addAttribute("estate", estate);
            return "html/moderEstatePage";
        } else {
            return "redirect:/";
        }

    }

    @GetMapping("/profileEstates")
    public String profileEstates(Model model) {
        UserModel currentUser = userService.getCurrentUserLogin();
        if (currentUser != null) {
            Long id = currentUser.getId();

            model.addAttribute("currentUser", currentUser);
            return "html/profile_estates";
        } else {
            return "redirect:/login";
        }
    }

//    @GetMapping("/reviewsPage")
//    public String reviews_page(Model model) {
//        UserModel currentUser = userService.getCurrentUserLogin();
//        if (currentUser != null) {
//            model.addAttribute("currentUser", currentUser);
//            return "html/reviews_page";
//        } else {
//            return "redirect:/login";
//        }
//    }
    @GetMapping("/userComplaint/{id}")
    public String userComplaint(@PathVariable Long id, Model model) {
        UserModel currentUser = userService.getCurrentUserLogin();

        model.addAttribute("currentUser", currentUser);
        Optional<ComplaintModel> complaintModel = complaintRepository.findById(id);
        if (complaintModel.isPresent()) {
            ComplaintModel complaint  = complaintModel.get();
            UserModel sender = complaint.getSender();
            UserModel recipient = complaint.getSender();
            model.addAttribute("complaint", complaint);
            model.addAttribute("senderUser", sender);
            model.addAttribute("recipientUser", recipient);
            return "html/userComplaint";
        } else {
            return "redirect:/";
        }

    }
    @GetMapping("/getAllComplaints")
    public String getAllComplaints(Model model) {
        UserModel currentUser = userService.getCurrentUserLogin();
        if (currentUser != null) {
            model.addAttribute("currentUser", currentUser);
            return "html/reviews_page";
        } else {
            return "redirect:/login";
        }

    }

    @RequestMapping("/updateComplaint/{id}")
    public ResponseEntity<String> updateComplaint(@PathVariable Long id, @RequestBody ComplaintModel complaintModel) {
        UserModel currentUser = userService.getCurrentUserLogin();
        Optional<ComplaintModel> existingComplaintOptional = complaintRepository.findById(id);
        if (existingComplaintOptional.isPresent()){
            ComplaintModel existingComplaint = existingComplaintOptional.get();
            existingComplaint.setModerator(currentUser);
            existingComplaint.setConclusion(complaintModel.getConclusion());
            existingComplaint.setBanType(complaintModel.getBanType());
            complaintRepository.save(existingComplaint);
        }

        return ResponseEntity.status(HttpStatus.OK).body("Estate created successfully");

    }
}