package mutsa.ex4.controller;

import lombok.RequiredArgsConstructor;
import mutsa.ex4.dto.request.LikesRequestDto;
import mutsa.ex4.service.LikesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LikesController {
    //특정 게시글에 대한 좋아요 생성
    private final LikesService likesService;

    @PostMapping("/api/v0/posts/{postId}/likes")
    public ResponseEntity<Long> createLikes(@PathVariable Long postId, LikesRequestDto likesRequestDto) {
        return ResponseEntity.status(201).body(likesService.createLikes(postId, likesRequestDto));

    }
}
