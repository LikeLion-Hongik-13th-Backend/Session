package prac1.mutsa2.User.service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import prac1.mutsa2.User.dto.UserCreateDto;
import prac1.mutsa2.User.entity.Follow;
import prac1.mutsa2.User.entity.User;
import prac1.mutsa2.User.repository.FollowRepository;
import prac1.mutsa2.User.repository.UserRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final FollowRepository followRepository;

    public User createUser(UserCreateDto userCreateDto){
        User user = User.builder()
                .name(userCreateDto.getName())
                .age(userCreateDto.getAge())
                .email(userCreateDto.getEmail())
                .createdAt(LocalDateTime.now())
                .build();
        return userRepository.save(user);
    }

    public void follow(Long fromId, Long toId){
        User follower = userRepository.findById(fromId)
                .orElseThrow(() -> new IllegalArgumentException("팔로우 요청자 없음"));
        User following = userRepository.findById(toId)
                .orElseThrow(() -> new IllegalArgumentException("팔로우 대상 없음"));

        if (followRepository.existsByFollowerAndFollowing(follower, following)) {
            throw new IllegalStateException("이미 팔로우 중입니다.");
        }
        Follow follow = Follow.builder()
                .follower(follower)
                .following(following)
                .createdAt(LocalDateTime.now())
                .build();
        followRepository.save(follow);
    }
}
