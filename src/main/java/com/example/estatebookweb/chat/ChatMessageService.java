package com.example.estatebookweb.chat;

import com.example.estatebookweb.chat.chatRoom.ChatRoomService;
import com.example.estatebookweb.repositories.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMessageService {
    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomService chatRoomService;

    public ChatMessage save(ChatMessage chatMessage){
        var chatId = chatRoomService
                .getChatRoomId(chatMessage.getSenderId(),
                chatMessage.getRecipientId(),
                        true )
                .orElseThrow();
        chatMessage.setChatId(chatId);
        chatMessageRepository.save(chatMessage);
        return chatMessage;
    }
    public List<ChatMessage> findChatMessages(String senderId, String recipientId){
        var chatId = chatRoomService.getChatRoomId(senderId, recipientId, false  );
        return chatId.map(chatMessageRepository::findAllByChatId).orElse(new ArrayList<>());
    }
}
