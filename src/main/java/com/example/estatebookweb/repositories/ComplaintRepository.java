package com.example.estatebookweb.repositories;

import com.example.estatebookweb.chat.chatRoom.ChatRoom;
import com.example.estatebookweb.models.ComplaintModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplaintRepository extends JpaRepository<ComplaintModel, Long> {
}