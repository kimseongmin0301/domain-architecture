package com.example.proto.global.common;

public record ResponseDto<T>(int status, String message, T data, Pagination pagination) {

    // 성공 응답 생성
    public static <T> ResponseDto<T> success(T data, String message) {
        return new ResponseDto<>(200, message, data, null);
    }

    // 성공 응답 생성 (페이징 포함)
    public static <T> ResponseDto<T> success(T data, String message, Pagination pagination) {
        return new ResponseDto<>(200, message, data, pagination);
    }

    // 실패 응답 생성
    public static <T> ResponseDto<T> error(int status, String message) {
        return new ResponseDto<>(status, message, null, null);
    }

    // Pagination record
    public record Pagination(int currentPage, int totalPages, int pageSize, long totalCount, int offset) {

        public Pagination {
        }
    }
}
