package mutsa.mutsa_practice6.exception;

import mutsa.mutsa_practice6.dto.response.ApiResponse;
import mutsa.mutsa_practice6.dto.response.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiResponse<Void>> handleCustomException(CustomException ex) {
        ErrorCode code = ex.getErrorCode();
        ErrorResponse errorResponse = new ErrorResponse(code.getCode(), code.getMessage());
        return ResponseEntity
                .status(code.getStatus())
                .body(ApiResponse.error(errorResponse));
    }
}
