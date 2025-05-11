package prac1.mutsa2.User.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import prac1.mutsa2.User.dto.UserCreateDto;
import prac1.mutsa2.User.service.UserService;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<String> createUsers(@RequestBody UserCreateDto userCreateDto) {
        try {
            userService.createUser(userCreateDto);
            return ResponseEntity.ok("사용자 생성 성공");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("사용자 생성 실패: " + e.getMessage());
        }
    }
    @PostMapping("/follow")
    public ResponseEntity<String> followUser(@RequestParam Long fromUserId, @RequestParam Long toUserId) {
        userService.follow(fromUserId, toUserId);
        return ResponseEntity.ok("팔로우 완료");
    }



}
