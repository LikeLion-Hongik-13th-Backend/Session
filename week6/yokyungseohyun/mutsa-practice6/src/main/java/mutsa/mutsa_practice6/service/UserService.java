package mutsa.mutsa_practice6.service;

import lombok.RequiredArgsConstructor;
import mutsa.mutsa_practice6.dto.request.UserRequestDto;
import mutsa.mutsa_practice6.entity.User;
import mutsa.mutsa_practice6.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Long createUser(UserRequestDto userRequestDto) {
        User user = User.builder()
                .userName(userRequestDto.getUserName())
                .email(userRequestDto.getEmail())
                .password(userRequestDto.getPassword())
                .build();

        return userRepository.save(user).getUserId();
    }
}