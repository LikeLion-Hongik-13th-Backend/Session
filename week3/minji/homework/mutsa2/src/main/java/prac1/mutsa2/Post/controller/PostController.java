package prac1.mutsa2.Post.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import prac1.mutsa2.Post.dto.LikeRequestDto;
import prac1.mutsa2.Post.dto.PostRequestDto;
import prac1.mutsa2.Post.dto.PostResponseDto;
import prac1.mutsa2.Post.entity.Post;
import prac1.mutsa2.Post.service.PostService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/posts")
@AllArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/create")
    public ResponseEntity<String> createPosts(@RequestBody PostRequestDto postRequestDto){
        try {
            postService.createPost(postRequestDto);
            return ResponseEntity.ok("게시물 생성 성공");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("게시물 생성 실패: " + e.getMessage());
        }
    }
    @GetMapping("/{postId}")
    public ResponseEntity<PostResponseDto> getPosts(@PathVariable Long postId){
        Post post = postService.findPostById(postId);
        return ResponseEntity.ok(PostResponseDto.from(post));
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PostResponseDto>> getPostsByUser(@PathVariable Long userId) {
        List<Post> posts = postService.findPostsByUserId(userId);
        List<PostResponseDto> response = posts.stream()
                .map(PostResponseDto::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
    // 좋아요
    @PostMapping("/like")
    public ResponseEntity<String> likePosts(@RequestBody LikeRequestDto likeRequestDto){
        postService.likePost(likeRequestDto);
        return ResponseEntity.ok("게시물 좋아요 성공");
    }

    @PostMapping("/unlike")
    public ResponseEntity<String> unlikePosts(@RequestBody LikeRequestDto likerequestDto){
        postService.unlikePost(likerequestDto);
        return ResponseEntity.ok("게시물 좋아요 취소");
    }

}
