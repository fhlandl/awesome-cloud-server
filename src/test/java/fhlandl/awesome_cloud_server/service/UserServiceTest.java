package fhlandl.awesome_cloud_server.service;

import fhlandl.awesome_cloud_server.domain.user.User;
import fhlandl.awesome_cloud_server.dto.user.LoginRequest;
import fhlandl.awesome_cloud_server.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class UserServiceTest {

    @Autowired UserService userService;

    @Autowired UserRepository userRepository;

    @Test
    void signup() {
        User user1 = User.builder()
            .loginId("id1")
            .password("pw1")
            .build();

        Long id1 = userService.signup(user1);
        User findUser1 = userRepository.findOne(id1);

        assertThat(findUser1.getLoginId()).isEqualTo(user1.getLoginId());
        assertThat(findUser1.getPassword()).isEqualTo(user1.getPassword());
    }

    @Test
    void signupFail() {
        User user1 = User.builder()
            .loginId("id1")
            .password("pw1")
            .build();

        User user2 = User.builder()
            .loginId("id1")
            .password("pw2")
            .build();

        userService.signup(user1);

        assertThatIllegalStateException().isThrownBy(() -> {
            userService.signup(user2);
        });
    }

    @Test
    void login() {
        User user1 = User.builder()
            .loginId("id1")
            .password("pw1")
            .build();

        userService.signup(user1);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setLoginId("id1");
        loginRequest.setPassword("pw1");

        User loginUser = userService.login(loginRequest);
        assertThat(loginUser.getLoginId()).isEqualTo(user1.getLoginId());
        assertThat(loginUser.getPassword()).isEqualTo(user1.getPassword());
    }

    @Test
    void loginFail() {
        User user1 = User.builder()
            .loginId("id1")
            .password("pw1")
            .build();

        userService.signup(user1);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setLoginId("id2");
        loginRequest.setPassword("pw1");

        User loginUser = userService.login(loginRequest);
        assertThat(loginUser).isNull();
    }
}