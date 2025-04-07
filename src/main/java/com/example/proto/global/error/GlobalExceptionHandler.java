package com.example.proto.global.error;

import com.example.proto.global.common.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    // 1. NullPointerException 처리
    // null 값을 참조할 때 발생
    @ExceptionHandler(NullPointerException.class)
    public ResponseDto<Void> handleNullPointerException(NullPointerException ex) {
        return ResponseDto.error(HttpStatus.BAD_REQUEST.value(), "NULL 값이 허용되지 않습니다.");
    }

    // 2. IllegalArgumentException 처리
    // 잘못된 인자가 전달 되었을 때 발생
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseDto<Void> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseDto.error(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    // 3. 요청 데이터 유효성 검사 실패 처리 (DTO 검증 실패)
    // @RequestBody나 @RequestParam 검증 시 발생
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseDto<Void> handleValidationException(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + " " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        return ResponseDto.error(HttpStatus.BAD_REQUEST.value(), "입력값 검증 실패: " + errorMessage);
    }

    // 4. 필수 요청 파라미터 누락
    // 필수 요청 파라미터가 누락되었을 때 발생
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseDto<Void> handleMissingRequestParam(MissingServletRequestParameterException ex) {
        return ResponseDto.error(HttpStatus.BAD_REQUEST.value(), "필수 요청 파라미터가 누락되었습니다: " + ex.getParameterName());
    }

    // 5. 지원되지 않는 HTTP 메서드 요청
    // 지원되지 않는 HTTP 메서드 호출 시 발생 (GET 대신 POST 요청 등)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseDto<Void> handleMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
        return ResponseDto.error(HttpStatus.METHOD_NOT_ALLOWED.value(), "지원되지 않는 HTTP 메서드입니다.");
    }

    // 6. 접근 권한 부족
    // Spring Security에서 인증은 되었지만 권한이 부족할 때 발생
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseDto<Void> handleAccessDenied(AccessDeniedException ex) {
        return ResponseDto.error(HttpStatus.FORBIDDEN.value(), "접근 권한이 없습니다.");
    }

    // 7. 기타 예상치 못한 예외 처리
    // 예상하지 못한 예외 발생 시 처리
    @ExceptionHandler(Exception.class)
    public ResponseDto<Void> handleGeneralException(Exception ex) {
        return ResponseDto.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "서버 오류 발생: " + ex.getMessage());
    }
}