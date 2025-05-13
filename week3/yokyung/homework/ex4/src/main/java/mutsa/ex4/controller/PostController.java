package mutsa.ex4.controller;

import lombok.RequiredArgsConstructor;
import mutsa.ex4.dto.request.PostRequestDto;
import mutsa.ex4.dto.response.PostResponseDto;
import mutsa.ex4.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("api/v0/posts/{userId}")
    public ResponseEntity<Long> createPost(@PathVariable Long userId, @RequestBody PostRequestDto postRequestDto) {
        return ResponseEntity.status(201).body(postService.createPost(userId, postRequestDto));
    }

    @GetMapping("api/v0/post")
    public ResponseEntity<PostResponseDto> getPost(@RequestParam Long postId) {
        return ResponseEntity.status(201).body(postService.getPost(postId));
    }

    @GetMapping("api/v0/posts")
    public ResponseEntity<List<PostResponseDto>> getPosts() {
        return ResponseEntity.status(201).body(postService.getAllPosts());
    }
}
