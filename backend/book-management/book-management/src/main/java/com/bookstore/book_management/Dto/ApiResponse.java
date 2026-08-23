package com.bookstore.book_management.Dto;

public class ApiResponse<T> {

    private int status;
    private String message;
    private T data;

    public ApiResponse() {
    }

    public ApiResponse(
            int status,
            String message,
            T data
    ) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    // api response status

    // =======================
    // SUCCESS
    // =======================

    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<>(
                200,
                "Success",
                data
        );
    }

    public static <T> ApiResponse<T> ok(
            T data,
            String message
    ) {
        return new ApiResponse<>(
                200,
                message,
                data
        );
    }

    public static <T> ApiResponse<T> created(T data) {
        return new ApiResponse<>(
                201,
                "Created successfully",
                data
        );
    }

    public static <T> ApiResponse<T> created(
            T data,
            String message
    ) {
        return new ApiResponse<>(
                201,
                message,
                data
        );
    }

    public static <T> ApiResponse<T> noContent() {
        return new ApiResponse<>(
                204,
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
                400,
                message,
                null
        );
    }

    public static <T> ApiResponse<T> unauthorized(
            String message
    ) {
        return new ApiResponse<>(
                401,
                message,
                null
        );
    }

    public static <T> ApiResponse<T> forbidden(
            String message
    ) {
        return new ApiResponse<>(
                403,
                message,
                null
        );
    }

    public static <T> ApiResponse<T> notFound(
            String message
    ) {
        return new ApiResponse<>(
                404,
                message,
                null
        );
    }

    public static <T> ApiResponse<T> conflict(
            String message
    ) {
        return new ApiResponse<>(
                409,
                message,
                null
        );
    }

    // =======================
    // SERVER ERROR
    // =======================

    public static <T> ApiResponse<T> internalServerError() {
        return new ApiResponse<>(
                500,
                "Internal server error",
                null
        );
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
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
