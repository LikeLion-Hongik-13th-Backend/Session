package prac1.mutsa2.Post.service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import prac1.mutsa2.Post.dto.CommentRequestDto;
import prac1.mutsa2.Post.entity.Post;
import prac1.mutsa2.Post.entity.PostComment;
import prac1.mutsa2.Post.repository.PostCommentRepository;
import prac1.mutsa2.Post.repository.PostRepository;
import prac1.mutsa2.User.entity.User;
import prac1.mutsa2.User.repository.UserRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final PostCommentRepository postCommentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;


    public void createComment (CommentRequestDto commentRequestDto){
        User user = userRepository.findById(commentRequestDto.getUserId())
                        .orElseThrow(() -> new RuntimeException("사용자 없음"));
        Post post = postRepository.findById(commentRequestDto.getPostId())
                        .orElseThrow(()-> new RuntimeException("없는 게시물"));
        PostComment comment = PostComment.builder()
                .comment(commentRequestDto.getComment())
                .user(user)
                .post(post)
                .createdAt(LocalDateTime.now())
                .build();
        postCommentRepository.save(comment);
    }

    public User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("사용자 없음"));
    }
}
