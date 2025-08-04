package mutsa.mutsa_practice6.controller;

import lombok.RequiredArgsConstructor;
import mutsa.mutsa_practice6.dto.request.UserRequestDto;
import mutsa.mutsa_practice6.dto.response.ApiResponse;
import mutsa.mutsa_practice6.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //상품 생성
    @PostMapping("/user")
    public ResponseEntity<ApiResponse<Long>> createUser(@RequestBody UserRequestDto userRequestDto) {
        return ResponseEntity.ok(ApiResponse.success(userService.createUser(userRequestDto)));
    }
}
