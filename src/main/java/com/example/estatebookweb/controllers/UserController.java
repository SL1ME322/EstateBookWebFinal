package com.example.estatebookweb.controllers;

import com.example.estatebookweb.EstateBookWebApplication;
import com.example.estatebookweb.models.*;
import com.example.estatebookweb.repositories.ComplaintRepository;
import com.example.estatebookweb.repositories.UserRepository;
import com.example.estatebookweb.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequiredArgsConstructor
public class UserController {
    static final Logger log =
            LoggerFactory.getLogger(EstateBookWebApplication.class);
    private final UserService service;
    private final ComplaintRepository complaintRepository;
    private final UserRepository userRepository;
    @MessageMapping("/users.addUser")
    @SendTo("/user/topic")
    public UserModel addUser(@Payload UserModel user) {
//        service.createUser(user);
        user.setRoles(Collections.singleton(RoleEnum.USER)  );
        user.setName("Роман");
        user.setSurname("Зюзин");
        user.setMiddleName("Андреевич");
        user.setAvatar("default_avatar.jpg");
        user.setPhone("+7-915-123-125-64");
        user.setLocation("г.Москва, ул. Пушкина 10");
        user.setDescription("Риэлтор недвижимости; 7 лет опыта");
        user.setAverageMark(4f);
        user.setRegistrationDate(new Date());
        user.setStatus(Collections.singleton(StatusEnum.ONLINE));
        user.setIsBanned(false);
        service.createUser(user);
        return user;
    }

    @GetMapping("/addUserComplaint")
    public String addUserComplaint(@RequestParam("senderId") Long senderId, @RequestParam("recipientId") Long recipientId, Model model) {
        model.addAttribute("senderId", senderId);
        model.addAttribute("recipientId", recipientId);
        return "html/addUserComplaint";
    }
    @RequestMapping("/createUserComplaint")
    public ResponseEntity<String> createUserComplaint(
            @RequestParam("senderId") Long senderId,
            @RequestParam("recipientId") Long recipientId,
            @RequestParam("type") String type,
            @RequestParam("description") String description) {

        UserModel sender = userRepository.getById(senderId);
        UserModel recipient = userRepository.getById(recipientId);

        type = type.replace(" ","_");
        try{
            ComplaintModel complaintModel = new ComplaintModel();
            complaintModel.setSender(sender);
            complaintModel.setRecipient(recipient);
            complaintModel.setType(Collections.singleton(UserComplaintEnum.valueOf(String.valueOf(type))));
            complaintModel.setDescription(description);
            complaintRepository.save(complaintModel);
            return ResponseEntity.status(HttpStatus.OK).body("Жалоба отправлена!");
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ошибка:"+ type);
        }
    }
    @GetMapping("/users")
    public ResponseEntity<List<UserModel>> findConnectedUsers() {
        return ResponseEntity.ok(service.findConnectedUsers());
    }
    @MessageMapping("/user.disconnectUser")
    @SendTo("/user/topic")
    public UserModel disconnectUser(@Payload UserModel user) {
        UserModel storedUser = userRepository.findByLogin(user.getLogin());
        if (storedUser != null) {
            storedUser.setStatus(Collections.singleton(StatusEnum.OFFLINE));
            log.info("Отключение пользователя: " + storedUser.getLogin());
            service.disconnect(storedUser);
        }
        return user;
    }

    @PostMapping("/users/{login}/status")
    public ResponseEntity<Map<String, String>> updateUserStatus(@PathVariable String login) {
        UserModel user = service.findByLogin(login);
        if (user != null) {
            Set<StatusEnum> statusSet = new HashSet<>();
            statusSet.add(StatusEnum.OFFLINE);
            user.setStatus(statusSet);
            service.saveUser(user);
            return ResponseEntity.ok(Collections.singletonMap("status", "OFFLINE"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("error", "User not found"));
        }
    }
    @GetMapping("/users/online")
    public ResponseEntity<List<UserModel>> getAllOnlineUsers() {
        List<UserModel> onlineUsers = service.findConnectedUsers();
        return ResponseEntity.ok(onlineUsers);
    }

}
