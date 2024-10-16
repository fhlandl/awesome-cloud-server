package fhlandl.awesome_cloud_server.controller;

import fhlandl.awesome_cloud_server.domain.user.User;
import fhlandl.awesome_cloud_server.dto.user.LoginRequest;
import fhlandl.awesome_cloud_server.dto.user.SignUpRequest;
import fhlandl.awesome_cloud_server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/new")
    public ResponseEntity<?> signup(@RequestBody SignUpRequest dto) {
        try {
            User user = User.builder()
                .loginId(dto.getLoginId())
                .password(dto.getPassword())
                .build();
            userService.signup(user);
            return ResponseEntity.ok("User created successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest dto) {
        User user = userService.login(dto);
        if (user == null) {
            return ResponseEntity.ok("User Login Failed");
        }
        return ResponseEntity.ok("User Logged in successfully");
    }
}
