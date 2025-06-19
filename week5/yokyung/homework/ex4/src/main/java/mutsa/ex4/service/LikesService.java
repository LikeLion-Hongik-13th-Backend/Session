package mutsa.ex4.service;

import lombok.RequiredArgsConstructor;
import mutsa.ex4.dto.request.LikesRequestDto;
import mutsa.ex4.entity.Likes;
import mutsa.ex4.entity.Post;
import mutsa.ex4.entity.User;
import mutsa.ex4.exception.CustomException;
import mutsa.ex4.repository.LikesRepository;
import mutsa.ex4.repository.PostRepository;
import mutsa.ex4.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikesService {
    private final LikesRepository likesRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public Long createLikes(Long postId, LikesRequestDto likesRequestDto) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException("해당 게시글이 존재하지 않습니다.", HttpStatus.NOT_FOUND));

        User user = userRepository.findById(likesRequestDto.getUserId())
                .orElseThrow(() -> new CustomException("해당 회원이 존재하지 않습니다.", HttpStatus.NOT_FOUND));

        Likes likes = Likes.builder()
                .post(post)
                .user(user)
                .build();
        return likesRepository.save(likes).getLikeId();
    }
}
