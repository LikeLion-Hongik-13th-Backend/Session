package mutsa.ex4.controller;

import lombok.RequiredArgsConstructor;
import mutsa.ex4.dto.request.FollowRequestDto;
import mutsa.ex4.dto.response.FollowResponseDto;
import mutsa.ex4.service.FollowService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FollowController {
    private final FollowService followService;

    //유저 간 팔로우 생성
    @PostMapping("/api/v0/users/{fromUserId}/followings")
    public ResponseEntity<Long> createFollow(@PathVariable Long fromUserId, @RequestBody FollowRequestDto followRequestDto) {
        return ResponseEntity.status(201).body(followService.createFollow(fromUserId, followRequestDto));
    }

    //팔로우/팔로잉 유저 전체 리스트 조회
    @GetMapping("/api/v0/follows")
    public ResponseEntity<List<FollowResponseDto>> getAllFollows(){
        return ResponseEntity.status(201).body(followService.getAllFollows());
    }
}
