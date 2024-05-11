package com.example.estatebookweb.filters;



import com.example.estatebookweb.models.UserModel;
import com.example.estatebookweb.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class PreAuthenticationFilter extends OncePerRequestFilter  {

    @Autowired
    private UserService userService;



    @Override
    protected void doFilterInternal(jakarta.servlet.http.HttpServletRequest request,
                                    jakarta.servlet.http.HttpServletResponse response,
                                    jakarta.servlet.FilterChain filterChain) throws jakarta.servlet.ServletException, IOException {
        String requestURI = request.getRequestURI();
        if (!requestURI.equals("/login") && !requestURI.equals("/register") ){
            UserModel currentUser = userService.getCurrentUserLogin();
            if (currentUser != null && currentUser.getIsBanned()){
                userService.updateUserBanStatus(currentUser);

                if (currentUser.getIsBanned()){
                    HttpSession session = request.getSession();
//                    session.setAttribute("bannedMessage", "Ваш аккаунт заблокирован до " + currentUser.getBannedUntilDate() + " из-за полученных жалоб.");
                    session.setAttribute("bannedMessage", "Ваш аккаунт заблокирован до 10/05/2024 из-за полученных жалоб.");
                    response.sendRedirect("/login");
                    return;
                }
            }
        }

        filterChain.doFilter(request, response);

    }


//    @Override
//    protected void doFilterInternal(jakarta.servlet.http.HttpServletRequest request,
//                                    jakarta.servlet.http.HttpServletResponse response,
//                                    jakarta.servlet.FilterChain filterChain) throws jakarta.servlet.ServletException, IOException {
//        String requestURI = request.getRequestURI();
//        if (!requestURI.equals("login") && !requestURI.equals("/register")){
//            UserModel currentUser = userService.getCurrentUserLogin();
//            if (currentUser != null && currentUser.getIsBanned()){
//                userService.updateUserBanStatus(currentUser);
//
//                if (currentUser.getIsBanned()){
//                    HttpSession session = request.getSession();
//                    session.setAttribute("bannedMessage", "Ваш аккаунт заблокирован до " + currentUser.getBannedUntilDate() + " из-за полученных жалоб.");
//                    response.sendRedirect("/login");
//                    return;
//                }
//            }
//        }
//
//        filterChain.doFilter(request, response);
//
//    }
}