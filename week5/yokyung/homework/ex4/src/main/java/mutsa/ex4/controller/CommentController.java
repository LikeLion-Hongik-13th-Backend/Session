package mutsa.ex4.controller;

import lombok.RequiredArgsConstructor;
import mutsa.ex4.dto.request.CommentRequestDto;
import mutsa.ex4.dto.response.CommentResponseDto;
import mutsa.ex4.entity.Comment;
import mutsa.ex4.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    //특정 게시글에 대한 댓글 생성
    @PostMapping("/api/v0/posts/{postId}/comments")
    public ResponseEntity<Long> createComment(@PathVariable Long postId, @RequestBody CommentRequestDto commentRequestDto) {
        return ResponseEntity.status(201).body(commentService.createComment(postId, commentRequestDto));
    }

    //특정 게시글에 대한 댓글 조회
    @GetMapping("/api/v0/posts/{postId}/comments")
    public ResponseEntity<List<CommentResponseDto>> getComments(@PathVariable Long postId) {
        return ResponseEntity.status(201).body(commentService.getComments(postId));
    }

}
