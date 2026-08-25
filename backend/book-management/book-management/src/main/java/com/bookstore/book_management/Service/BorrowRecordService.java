/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.bookstore.book_management.Service;

/**
 *
 * @author Admin
 */
import java.time.LocalDateTime;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bookstore.book_management.Dto.ApiResponse;
import com.bookstore.book_management.Entity.BorrowRecord;
import com.bookstore.book_management.Repository.BorrowRecordRepository;

@Service
public class BorrowRecordService {
    private final BorrowRecordRepository borrowRecordRepository;

    private final JwtService jwtService;

    public BorrowRecordService(
        BorrowRecordRepository borrowRecordRepository,
        JwtService jwtService
    ) {
        this.borrowRecordRepository = borrowRecordRepository;
        this.jwtService = jwtService;
    }

    public ApiResponse<?> createBorrowRecord(BorrowRecord borrowRecord) {
        try {

            borrowRecordRepository.save(borrowRecord);
            return ApiResponse.created(null);


        } catch (Exception e) {
            return ApiResponse.internalServerError();
        }
    }

    public ApiResponse<?> getBorrowRecordById(
            Long id,
            Authentication authentication
    ) {

        BorrowRecord borrowRecord =
                borrowRecordRepository
                        .findById(id)
                        .orElse(null);

        if (borrowRecord == null) {
            return ApiResponse.notFound(
                    "Borrow record not found"
            );
        }

        if (!jwtService.canAccessUser(
                authentication,
                borrowRecord.getUser().getId()
        )) {

            return ApiResponse.forbidden(
                    "Access denied"
            );
        }

        return ApiResponse.ok(borrowRecord);
    }

    public ApiResponse<?> updateBorrowRecord(
            Long id,
            BorrowRecord updatedBorrowRecord
    ) {

        BorrowRecord existingBorrowRecord =
                borrowRecordRepository
                        .findById(id)
                        .orElse(null);

        if (existingBorrowRecord == null) {

            return ApiResponse.notFound(
                    "Borrow record not found"
            );
        }

        try {

            existingBorrowRecord.setUser(
                    updatedBorrowRecord.getUser()
            );

            existingBorrowRecord.setBook(
                    updatedBorrowRecord.getBook()
            );

            existingBorrowRecord.setBorrowDate(
                    updatedBorrowRecord.getBorrowDate()
            );

            existingBorrowRecord.setReturnDate(
                    updatedBorrowRecord.getReturnDate()
            );

            BorrowRecord saved = borrowRecordRepository.save(existingBorrowRecord);

            return ApiResponse.ok(
                    saved,
                    "Borrow record updated successfully"
            );

        } catch (Exception e) {

            return ApiResponse.internalServerError();
        }
    }

    @Transactional
    public ApiResponse<?> deleteBorrowRecord(Long id) {

        BorrowRecord borrowRecord =
                borrowRecordRepository
                        .findById(id)
                        .orElse(null);

        if (borrowRecord == null) {

            return ApiResponse.notFound(
                    "Borrow record not found"
            );
        }

        try {

            borrowRecordRepository.delete(
                    borrowRecord
            );

            return ApiResponse.noContent();

        } catch (Exception e) {

            return ApiResponse.internalServerError();
        }
    }

    public ApiResponse<?> getAllBorrowRecords() {

        return ApiResponse.ok(
                borrowRecordRepository.findAll()
        );
    }

    public ApiResponse<?> getBorrowRecordsByBookId(
            Long bookId
    ) {

        return ApiResponse.ok(
                borrowRecordRepository
                        .findByBookId(bookId)
        );
    }

    public ApiResponse<?> getBorrowRecordsByUserId(
        Long userId,
        Authentication authentication
    ) {

        if (!jwtService.canAccessUser(
                authentication,
                userId
        )) {

            return ApiResponse.forbidden(
                    "Access denied"
            );
        }

        return ApiResponse.ok(
                borrowRecordRepository
                        .findByUserId(userId)
        );
    }

    public ApiResponse<?> getBorrowRecordsByUserIdAndBookId(
            Long userId,
            Long bookId,
            Authentication authentication
    ) {

        if (!jwtService.canAccessUser(
                authentication,
                userId
        )) {

            return ApiResponse.forbidden(
                    "Access denied"
            );
        }

        return ApiResponse.ok(
                borrowRecordRepository
                        .findByUserIdAndBookId(
                                userId,
                                bookId
                        )
        );
    }

    public ApiResponse<?> setReturnDate(
            Long borrowRecordId,
            LocalDateTime returnDate
    ) {

        BorrowRecord borrowRecord =
                borrowRecordRepository
                        .findById(borrowRecordId)
                        .orElse(null);

        if (borrowRecord == null) {

            return ApiResponse.notFound(
                    "Borrow record not found"
            );
        }

        try {

            borrowRecord.setReturnDate(returnDate);
            borrowRecordRepository.save(borrowRecord);

            return ApiResponse.ok(
                    null,
                    "Return date updated successfully"
            );

        } catch (Exception e) {

            return ApiResponse.internalServerError();
        }
    }
}
