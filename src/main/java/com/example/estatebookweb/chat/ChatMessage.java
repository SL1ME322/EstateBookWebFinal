package com.example.estatebookweb.chat;

import jakarta.persistence.*;
import lombok.*;

import java.awt.*;
import java.util.Date;
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "ChatMessage")
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ChatMessage")
    private Long id;
    @Column(name = "Chat_ID")
    private String chatId;
    @Column(name = "Sender_ID")
    private String senderId;
    @Column(name = "Recipient_ID")
    private String recipientId;
    @Column(name = "Content" ,  columnDefinition = "NVARCHAR(255)")
    private String content;
    @Column(name = "Timestamp")
    private Date timestamp;
}