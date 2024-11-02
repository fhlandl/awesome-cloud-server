package fhlandl.awesome_cloud_server.service;

import fhlandl.awesome_cloud_server.domain.user.User;
import fhlandl.awesome_cloud_server.dto.user.LoginRequest;
import fhlandl.awesome_cloud_server.dto.user.SignUpRequest;
import fhlandl.awesome_cloud_server.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class UserServiceTest {

    @Autowired UserService userService;

    @Autowired UserRepository userRepository;

    @Autowired BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    void signup() {
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setLoginId("id1");
        signUpRequest.setPassword("pw1");

        Long id1 = userService.signup(signUpRequest);
        User findUser1 = userRepository.findOne(id1);

        assertThat(findUser1.getLoginId()).isEqualTo(signUpRequest.getLoginId());
        assertThat(bCryptPasswordEncoder.matches(signUpRequest.getPassword(), findUser1.getPassword())).isTrue();
    }

    @Test
    void signupFail() {
        SignUpRequest signUpRequest1 = new SignUpRequest();
        signUpRequest1.setLoginId("id1");
        signUpRequest1.setPassword("pw1");

        SignUpRequest signUpRequest2 = new SignUpRequest();
        signUpRequest2.setLoginId("id1");
        signUpRequest2.setPassword("pw2");

        userService.signup(signUpRequest1);

        assertThatIllegalStateException().isThrownBy(() -> {
            userService.signup(signUpRequest2);
        });
    }

    @Test
    void login() {
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setLoginId("id1");
        signUpRequest.setPassword("pw1");

        userService.signup(signUpRequest);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setLoginId("id1");
        loginRequest.setPassword("pw1");

        User loginUser = userService.login(loginRequest);
        assertThat(loginUser.getLoginId()).isEqualTo(signUpRequest.getLoginId());
        assertThat(bCryptPasswordEncoder.matches(signUpRequest.getPassword(), loginUser.getPassword())).isTrue();
    }

    @Test
    void loginFail() {
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setLoginId("id1");
        signUpRequest.setPassword("pw1");

        userService.signup(signUpRequest);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setLoginId("id2");
        loginRequest.setPassword("pw1");

        User loginUser = userService.login(loginRequest);
        assertThat(loginUser).isNull();
    }
}
