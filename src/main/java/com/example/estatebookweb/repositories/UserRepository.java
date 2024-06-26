package com.example.estatebookweb.repositories;



import com.example.estatebookweb.models.StatusEnum;
import com.example.estatebookweb.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    @Query("SELECT u FROM UserModel u WHERE u.login = :login")
    UserModel findByLogin(@Param("login") String login);

    List<UserModel> findAllByStatus(StatusEnum status);

    UserModel getById(Long id);
    UserModel getByLogin(String login);
}
