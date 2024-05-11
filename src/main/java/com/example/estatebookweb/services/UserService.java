package com.example.estatebookweb.services;

import com.example.estatebookweb.models.StatusEnum;
import com.example.estatebookweb.models.UserModel;
import com.example.estatebookweb.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service

public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public UserModel getCurrentUserLogin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
               String userName = ((UserDetails) principal).getUsername();
                return userRepository.findByLogin(userName);
            }
        }
        return null;
    }
    public UserModel getUserByLogin(String login){
        UserModel user = userRepository.findByLogin(login);
        return user;
    }
    public UserModel createUser(UserModel user){
        user.setStatus(Collections.singleton(StatusEnum.ONLINE));
        UserModel newUser = userRepository.save(user);
        userRepository.flush();
        return newUser;
    }
    public void disconnect(UserModel user)
    {
        UserModel storedUser = userRepository.findByLogin(user.getName());
        if (storedUser != null){
            storedUser.setStatus(Collections.singleton(StatusEnum.OFFLINE));
            userRepository.save(storedUser);
        }
    }
    public List<UserModel> findConnectedUsers(){
        return userRepository.findAllByStatus(StatusEnum.ONLINE);
    }
    public void updateUserBanStatus(UserModel user){
        if (user.getBannedUntilDate() != null && user.getBannedUntilDate().isBefore(LocalDateTime.now())){
            user.setBannedUntilDate(null);
            user.setIsBanned(false);
            userRepository.save(user);
        }
    }

}
