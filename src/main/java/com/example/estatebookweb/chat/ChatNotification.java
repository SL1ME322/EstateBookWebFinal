package com.example.estatebookweb.chat;

import jakarta.persistence.*;
import lombok.*;
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "ChatNotification")
public class ChatNotification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ChatNotification")
    private Long id;
    @Column(name = "Sender_ID")
    private String senderId;
    @Column(name = "Recipient_ID")
    private String recipientId;
    @Column(name = "Content")
    private String content;
}
