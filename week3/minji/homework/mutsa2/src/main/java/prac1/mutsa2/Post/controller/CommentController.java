package prac1.mutsa2.Post.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import prac1.mutsa2.Post.dto.CommentRequestDto;
import prac1.mutsa2.Post.dto.PostRequestDto;
import prac1.mutsa2.Post.service.CommentService;

@RestController
@RequestMapping("api/v1/comments")
@AllArgsConstructor
public class CommentController {

    private final CommentService commentService;
    @PostMapping("/create")
    public ResponseEntity<String> createPosts(@RequestBody CommentRequestDto commentrequestDto){
        try {
            commentService.createComment(commentrequestDto);
            return ResponseEntity.ok("댓글 작성 성공");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("댓글 작성 실패: " + e.getMessage());
        }
    }
}
