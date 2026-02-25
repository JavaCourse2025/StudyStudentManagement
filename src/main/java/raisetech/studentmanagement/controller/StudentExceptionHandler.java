package raisetech.studentmanagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice // ← 「エラーが起きたら僕の出番だよ！」という呪文
public class StudentExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class) // ← バリデーションエラーを捕まえる
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        // 「どの項目」が「どうダメだったか」を全部集める
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        return ResponseEntity.badRequest().body(errors); // きれいなJSONでお返事！
    }
}