package com.Hospital_System.demo.Security;

import com.Hospital_System.demo.Entity.Role;
import com.Hospital_System.demo.Entity.UserEntity;
import com.Hospital_System.demo.Repository.UserRepo;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Component
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserRepo userRepo;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        String email = oAuth2User.getAttribute("email");
        Set<Role> userRole = new HashSet<>();

        UserEntity userEntity = userRepo.findByUserName(email);
        if (userEntity == null) {

            UserEntity user = new UserEntity();
            user.setUserName(email);
            user.setPassword("password");
            userRole.add(Role.PATIENT);
            user.setRoles(userRole);

            userRepo.save(user);
            System.out.println(" PATIENT CREATE SUCCESSFULLY: " + email);


        } else {
            userRole = userEntity.getRoles();
            System.out.println("Purana user wapas aa gaya: " + email);
        }

        String token = jwtUtil.generateToken(email, userRole);
        response.setContentType("application/json");
        response.getWriter().write("{\n" +
                "  \"message\": \"Google Login Successful!\",\n" +
                "  \"email\": \"" + email + "\",\n" +
                "  \"token\": \"" + token + "\"\n" +
                "}");
    }
}
