package com.example.estatebookweb.chat.chatRoom;

import jakarta.persistence.*;
import lombok.*;
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "ChatRoom")
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ChatRoom")
    private Long id;
    @Column(name = "Chat_ID")
    private String chatId;
    @Column(name = "Sender_ID")
    private String senderId;
    @Column(name = "Recipient_ID")
    private String recipientId;
}
