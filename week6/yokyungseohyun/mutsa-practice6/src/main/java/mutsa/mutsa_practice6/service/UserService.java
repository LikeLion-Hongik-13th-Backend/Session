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
                .nickname(userRequestDto.getNickname())
                .build();

        return userRepository.save(user).getUserId();
    }
}