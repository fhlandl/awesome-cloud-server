package fhlandl.awesome_cloud_server.service;

import fhlandl.awesome_cloud_server.domain.user.User;
import fhlandl.awesome_cloud_server.dto.user.LoginRequest;
import fhlandl.awesome_cloud_server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public Long signup(User user) {
        validateDuplicateUser(user);
        userRepository.save(user);
        return user.getId();
    }

    private void validateDuplicateUser(User user) {
        List<User> users = userRepository.findByLoginId(user.getLoginId());
        if (!users.isEmpty()) {
            throw new IllegalStateException("This user is already existing");
        }
    }

    public User login(LoginRequest dto) {
        return userRepository.findByLoginId(dto.getLoginId())
            .stream().filter(u -> u.getPassword().equals(dto.getPassword()))
            .findFirst()
            .orElse(null);
    }
}
