package mutsa.mutsa_practice6.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    INVALID_REQUEST("INVALID_REQUEST", HttpStatus.BAD_REQUEST, "필수 항목이 누락되었습니다"),
    DUPLICATED_PRODUCT("DUPLICATED_PRODUCT", HttpStatus.BAD_REQUEST, "중복된 상품이 있습니다"),
    INVALID_TYPE("INVALID_TYPE", HttpStatus.BAD_REQUEST, "입력 형식이 올바르지 않습니다"),
    UNAUTHORIZED("UNAUTHORIZED", HttpStatus.UNAUTHORIZED, "로그인 정보가 유효하지 않습니다"),
    FORBIDDEN("FORBIDDEN", HttpStatus.FORBIDDEN, "이 기능에 접근 권한이 없습니다"),
    NOT_FOUND("NOT_FOUND", HttpStatus.NOT_FOUND, "요청한 리소스를 찾을 수 없습니다"),
    PRODUCT_NOT_FOUND("PRODUCT_NOT_FOUND", HttpStatus.NOT_FOUND, "해당 상품은 존재하지 않습니다"),
    CART_NOT_FOUND("CART_NOT_FOUND", HttpStatus.NOT_FOUND, "장바구니가 존재하지 않습니다"),
    USER_NOT_FOUND("USER_NOT_FOUND", HttpStatus.NOT_FOUND, "해당 유저는 존재하지 않습니다"),
    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류가 발생했습니다");

    private final String code;
    private final HttpStatus status;
    private final String message;
}
