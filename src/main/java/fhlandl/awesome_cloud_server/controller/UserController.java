package fhlandl.awesome_cloud_server.controller;

import fhlandl.awesome_cloud_server.domain.user.User;
import fhlandl.awesome_cloud_server.dto.user.LoginRequest;
import fhlandl.awesome_cloud_server.dto.user.SignUpRequest;
import fhlandl.awesome_cloud_server.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/new")
    public ResponseEntity<?> signup(@RequestBody @Valid SignUpRequest dto) {
        try {
            userService.signup(dto);
            return ResponseEntity.ok("User created successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest dto, HttpServletRequest request) {
        try {
            User user = userService.login(dto);
            if (user == null) {
                throw new NoSuchElementException("User Login Failed");
            }

            HttpSession session = request.getSession();
            session.setAttribute(SessionConst.LOGIN_MEMBER, user);
            log.info("Session Created");

            return ResponseEntity.ok("User Logged in successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
            log.info("Session Removed");
        }
        return ResponseEntity.ok("User logged out successfully");
    }
}
