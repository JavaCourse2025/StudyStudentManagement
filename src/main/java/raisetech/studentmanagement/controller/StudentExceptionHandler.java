package raisetech.studentmanagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import raisetech.studentmanagement.domain.ApiResponse;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice(assignableTypes = {StudentApiController.class})
public class StudentExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class) // ← バリデーションエラーを捕まえる
    public ResponseEntity<ApiResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        // 「どの項目」が「どうダメだったか」を全部集める
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        ApiResponse response = new ApiResponse(false, "エラーが発生しました", errors);

        return ResponseEntity.badRequest().body(response); // きれいなJSONでお返事！
    }
}