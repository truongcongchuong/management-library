/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.bookstore.book_management.Controller;

/**
 *
 * @author Admin
 */
import java.time.LocalDateTime;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.book_management.Dto.ApiResponse;
import com.bookstore.book_management.Entity.BorrowRecord;
import com.bookstore.book_management.Service.BorrowRecordService;

@RestController
@RequestMapping("/api/borrow-records")
public class BorrowRecordController {

    private final BorrowRecordService service;

    public BorrowRecordController(BorrowRecordService service) {
        this.service = service;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<?> getAllBorrowRecords() {
        return service.getAllBorrowRecords();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ApiResponse<?> getBorrowRecordById(@PathVariable Long id,  Authentication authentication) {
        return service.getBorrowRecordById(id, authentication);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<?> createBorrowRecord(@RequestBody BorrowRecord borrowRecord) {
        return service.createBorrowRecord(borrowRecord);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<?> updateBorrowRecord(@PathVariable Long id, @RequestBody BorrowRecord updatedBorrowRecord) {
        return service.updateBorrowRecord(id, updatedBorrowRecord);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<?> deleteBorrowRecord(@PathVariable Long id) {
        return service.deleteBorrowRecord(id);
    }

    @GetMapping("/user/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ApiResponse<?> getBorrowRecordsByUserId(@PathVariable long id, Authentication authentication) {
        return service.getBorrowRecordsByUserId(id, authentication);
    }

    @GetMapping("/user/{userId}/book/{bookId}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ApiResponse<?> getBorrowRecordsByUserIdAndBookId(@PathVariable long userId, @PathVariable long bookId, Authentication authentication) {

        return service.getBorrowRecordsByUserIdAndBookId(userId, bookId, authentication);
    }
    
    @GetMapping("/returnBook/{borrowRecordId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<?>  setReturnBooks(@PathVariable long borrowRecordId) {
        return service.setReturnDate(borrowRecordId, LocalDateTime.now());
    }
    
    @GetMapping("/book/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<?> getBorrowRecordsByBookId(@PathVariable long id) {
        return service.getBorrowRecordsByBookId(id);
    }
}
