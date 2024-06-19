package com.example.estatebookweb;

import com.example.estatebookweb.models.RoleEnum;
import com.example.estatebookweb.models.StatusEnum;
import com.example.estatebookweb.models.UserModel;
import com.example.estatebookweb.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.logging.Logger;

@Controller
@RequestMapping("")
public class AuthController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    public AuthController(UserService userService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String createNewUser(HttpServletRequest request,
                                HttpServletResponse response, @ModelAttribute("user") UserModel user){
        user.setRoles(Collections.singleton(RoleEnum.MODERATOR)  );
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


        userService.createUser(user);
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getLogin(),user.getPassword()));
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);
        HttpSession session = request.getSession(true);
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,securityContext);
        return "redirect:/login";
    }
    //    @RequestMapping(value = "/register", method = RequestMethod.POST)
//    public String createNewUser(@RequestBody UserModel user) {
//
//        user.setRoles(Collections.singleton(RoleEnum.USER));
//        user.setName("Роман");
//        user.setSurname("Андреев");
//        user.setMiddleName("Андреевич");
//        user.setAvatar("default_avatar.jpg");
//        user.setPhone("+7-915-123-125-64");
//        user.setLocation("г.Москва, ул. Пушкина 10");
//        user.setDescription("Риэлтор недвижимости; 7 лет опыта");
//        user.setAverageMark(4f);
//        user.setRegistrationDate(new Date());
//        userService.createUser(user);
//        return "redirect:/login";
//    }
    @RequestMapping(value = "/login",method = RequestMethod.GET)

    public String login( ) {

        return "html/login";
    }

    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logout(HttpServletRequest response) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();

        UserModel user = userService.getUserByLogin(name) ;
        if (user != null) {
            user.setStatus(Collections.singleton(StatusEnum.OFFLINE));
            userService.createUser(user);
        }
        HttpSession session =  response.getSession();
        session.invalidate();
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(null);
        return "redirect:/login";
    }
    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String register(HttpServletRequest request, HttpServletResponse response, Model model) {
        UserModel user = new UserModel();
        model.addAttribute("user", user);
        return "html/register";
    }
    @RequestMapping(value = "/current-user-login", method = RequestMethod.GET)
    @ResponseBody
    public String getCurrentUserLogin() {
        UserModel currentUser = userService.getCurrentUserLogin();
        if (currentUser != null) {
            return currentUser.getLogin();
        } else {
            return null;
        }
    }
}
