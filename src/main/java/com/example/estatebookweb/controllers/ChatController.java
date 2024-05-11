package com.example.estatebookweb.controllers;

import com.example.estatebookweb.chat.ChatMessage;
import com.example.estatebookweb.chat.ChatMessageService;
import com.example.estatebookweb.chat.ChatNotification;
import com.example.estatebookweb.models.UserModel;
import com.example.estatebookweb.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChatController {
    private final SimpMessagingTemplate messagingTemplate;
    private final ChatMessageService chatMessageService;
    private final UserService userService;
@MessageMapping("/chat")
    public void processMessage(
            @Payload ChatMessage chatMessage
    ){
        ChatMessage savedMessage = chatMessageService.save(chatMessage);
        messagingTemplate.convertAndSendToUser(chatMessage.getRecipientId(),
                "/queue/messages",
                ChatNotification.builder()
                        .id(savedMessage.getId())
                        .senderId(savedMessage.getSenderId())
                        .recipientId(savedMessage.getRecipientId())
                        .content(savedMessage.getContent())
                        .build());
                ;
    }
    @GetMapping("/messages/{senderId}/{recipientId}")
    public ResponseEntity<List<ChatMessage>> findChatMessages(@PathVariable   String senderId,
                                                              @PathVariable   String recipientId){
        return ResponseEntity.ok(chatMessageService.findChatMessages(senderId,recipientId));
    }
    @GetMapping("/chat_one_test")
    public String getChatOneTest(Model model) {
        UserModel currentUser = userService.getCurrentUserLogin();
        if (currentUser != null) {
            model.addAttribute("currentUserLogin", currentUser.getLogin());
        }
        return "html/chat_one_test";
    }

}
