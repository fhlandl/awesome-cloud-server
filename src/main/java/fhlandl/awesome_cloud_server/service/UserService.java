package fhlandl.awesome_cloud_server.service;

import fhlandl.awesome_cloud_server.domain.user.User;
import fhlandl.awesome_cloud_server.dto.user.LoginRequest;
import fhlandl.awesome_cloud_server.dto.user.SignUpRequest;
import fhlandl.awesome_cloud_server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public Long signup(SignUpRequest dto) {
        validateDuplicateUser(dto.getLoginId());

        User user = User.builder()
                .loginId(dto.getLoginId())
                .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                .build();
        userRepository.save(user);
        return user.getId();
    }

    private void validateDuplicateUser(String loginId) {
        List<User> users = userRepository.findByLoginId(loginId);
        if (!users.isEmpty()) {
            throw new IllegalStateException("This user is already existing");
        }
    }

    public User login(LoginRequest dto) {
        return userRepository.findByLoginId(dto.getLoginId())
            .stream().filter(u -> bCryptPasswordEncoder.matches(dto.getPassword(), u.getPassword()))
            .findFirst()
            .orElse(null);
    }
}
