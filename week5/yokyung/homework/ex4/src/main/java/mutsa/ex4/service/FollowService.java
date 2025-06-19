package mutsa.ex4.service;

import lombok.RequiredArgsConstructor;
import mutsa.ex4.dto.request.FollowRequestDto;
import mutsa.ex4.dto.response.FollowResponseDto;
import mutsa.ex4.entity.Follow;
import mutsa.ex4.entity.User;
import mutsa.ex4.exception.CustomException;
import mutsa.ex4.repository.FollowRepository;
import mutsa.ex4.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FollowService {
    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    public Long createFollow(Long fromUserId, FollowRequestDto followRequestDto) {
        User fromUser = userRepository.findById(fromUserId)
                .orElseThrow(() -> new CustomException("로그인된 회원이 존재하지 않습니다.", HttpStatus.NOT_FOUND));

        User toUser = userRepository.findById(followRequestDto.getToUserId())
                .orElseThrow(() -> new CustomException("팔로우하려는 회원이 존재하지 않습니다.", HttpStatus.NOT_FOUND));

        Follow follow = Follow.builder()
                .fromUser(fromUser)
                .toUser(toUser)
                .build();

        return followRepository.save(follow).getFollowId();
    }

    public List<FollowResponseDto> getAllFollows() {
        List<FollowResponseDto> followResponseDtos = followRepository.findAll().stream()
                .map(follow -> new FollowResponseDto(
                        follow.getFollowId(),
                        follow.getFromUser().getId(),
                        follow.getToUser().getId()
                )).collect(Collectors.toList());
        return followResponseDtos;
    }
}
