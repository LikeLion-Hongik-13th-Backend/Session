package mutsa.ex4.service;

import lombok.RequiredArgsConstructor;
import mutsa.ex4.dto.request.CommentRequestDto;
import mutsa.ex4.dto.response.CommentResponseDto;
import mutsa.ex4.entity.Comment;
import mutsa.ex4.entity.Post;
import mutsa.ex4.entity.User;
import mutsa.ex4.exception.CustomException;
import mutsa.ex4.repository.CommentRepository;
import mutsa.ex4.repository.PostRepository;
import mutsa.ex4.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    //특정 게시글에 대한 댓글 생성
    public Long createComment(Long postId, CommentRequestDto commentRequestDto) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException("게시글이 존재하지 않습니다.", HttpStatus.NOT_FOUND));

        User user = userRepository.findById(commentRequestDto.getUserId())
                .orElseThrow(() -> new CustomException("유저가 존재하지 않습니다.", HttpStatus.NOT_FOUND));

        Comment comment = Comment.builder()
                .content(commentRequestDto.getContent())
                .post(post)
                .user(user)
                .build();
        return commentRepository.save(comment).getCommentId();
    }

    //특정 게시글에 대한 댓글 조회
    public List<CommentResponseDto> getComments(Long postId) {
        List<CommentResponseDto> commentResponseDtos = commentRepository.findAllByPostId(postId).stream()
                .map(comment -> new CommentResponseDto(
                        comment.getCommentId(),
                        comment.getPost().getPostId(),
                        comment.getUser().getId()
                )).collect(Collectors.toList());
        return commentResponseDtos;
    }
}
