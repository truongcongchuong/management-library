/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.bookstore.book_management.Controller;

/**
 *
 * @author Admin
 */
import java.time.LocalDate;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public List<BorrowRecord> getAllBorrowRecords() {
        return service.getAllBorrowRecords();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public BorrowRecord getBorrowRecordById(@PathVariable Long id) {
        return service.getBorrowRecordById(id);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public BorrowRecord createBorrowRecord(@RequestBody BorrowRecord borrowRecord) {
        return service.createBorrowRecord(borrowRecord);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public BorrowRecord updateBorrowRecord(@PathVariable Long id, @RequestBody BorrowRecord updatedBorrowRecord) {
        return service.updateBorrowRecord(id, updatedBorrowRecord);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteBorrowRecord(@PathVariable Long id) {
        service.deleteBorrowRecord(id);
    }

    @GetMapping("/user/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public List<BorrowRecord> getBorrowRecordsByUserId(@PathVariable long id) {
        return service.getBorrowRecordsByUserId(id);
    }

    @GetMapping("/user/{userId}/book/{bookId}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public List<BorrowRecord> getBorrowRecordsByUserIdAndBookId(@PathVariable long userId, @PathVariable long bookId) {
        return service.getBorrowRecordsByUserIdAndBookId(userId, bookId);
    }
    
    @GetMapping("/returnBook/{borrowRecordId}")
    @PreAuthorize("hasRole('ADMIN')")
    public boolean  setReturnBooks(@PathVariable long borrowRecordId) {
        return service.setReturnDate(borrowRecordId, LocalDate.now());
    }
    
    @GetMapping("/book/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<BorrowRecord> getBorrowRecordsByBookId(@PathVariable long id) {
        return service.getBorrowRecordsByBookId(id);
    }
}
