package com.bookstore.book_management.Dto;

import org.springframework.http.HttpStatus;

public class ApiResponse<T> {

    private HttpStatus status;
    private String message;
    private T data;

    public ApiResponse() {
    }

    public ApiResponse(
            HttpStatus status,
            String message,
            T data
    ) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    // =======================
    // SUCCESS
    // =======================

    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<>(
                HttpStatus.OK,
                "Success",
                data
        );
    }

    public static <T> ApiResponse<T> ok(
            T data,
            String message
    ) {
        return new ApiResponse<>(
                HttpStatus.OK,
                message,
                data
        );
    }

    public static <T> ApiResponse<T> created(T data) {
        return new ApiResponse<>(
                HttpStatus.CREATED,
                "Created successfully",
                data
        );
    }

    public static <T> ApiResponse<T> created(
            T data,
            String message
    ) {
        return new ApiResponse<>(
                HttpStatus.CREATED,
                message,
                data
        );
    }

    public static <T> ApiResponse<T> noContent() {
        return new ApiResponse<>(
                HttpStatus.NO_CONTENT,
                "No content",
                null
        );
    }

    // =======================
    // CLIENT ERROR
    // =======================

    public static <T> ApiResponse<T> badRequest(
            String message
    ) {
        return new ApiResponse<>(
                HttpStatus.BAD_REQUEST,
                message,
                null
        );
    }

    public static <T> ApiResponse<T> unauthorized(
            String message
    ) {
        return new ApiResponse<>(
                HttpStatus.UNAUTHORIZED,
                message,
                null
        );
    }

    public static <T> ApiResponse<T> forbidden(
            String message
    ) {
        return new ApiResponse<>(
                HttpStatus.FORBIDDEN,
                message,
                null
        );
    }

    public static <T> ApiResponse<T> notFound(
            String message
    ) {
        return new ApiResponse<>(
                HttpStatus.NOT_FOUND,
                message,
                null
        );
    }

    public static <T> ApiResponse<T> conflict(
            String message
    ) {
        return new ApiResponse<>(
                HttpStatus.CONFLICT,
                message,
                null
        );
    }

    // =======================
    // SERVER ERROR
    // =======================

    public static <T> ApiResponse<T> internalServerError() {
        return new ApiResponse<>(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Internal server error",
                null
        );
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}