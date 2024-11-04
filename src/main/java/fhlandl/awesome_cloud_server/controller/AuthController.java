package fhlandl.awesome_cloud_server.controller;

import fhlandl.awesome_cloud_server.domain.user.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @GetMapping
    public ResponseEntity<?> homeLogin(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) User loginUser) {

        if (loginUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Session Not Found");
        }

        return ResponseEntity.ok("Session Found");
    }

}
