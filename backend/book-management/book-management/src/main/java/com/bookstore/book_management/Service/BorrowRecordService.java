/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.bookstore.book_management.Service;

/**
 *
 * @author Admin
 */
import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.bookstore.book_management.Entity.BorrowRecord;
import com.bookstore.book_management.Repository.BorrowRecordRepository;

@Service
public class BorrowRecordService {
    private final BorrowRecordRepository borrowRecordRepository;
    private final AuthService authService;

    public BorrowRecordService(BorrowRecordRepository borrowRecordRepository, AuthService authService) {
        this.borrowRecordRepository = borrowRecordRepository;
        this.authService = authService;
    }

    public BorrowRecord createBorrowRecord(BorrowRecord borrowRecord) {
        return borrowRecordRepository.save(borrowRecord);
    }

    public BorrowRecord getBorrowRecordById(Long id, Authentication authentication) {

        BorrowRecord borrowRecord = borrowRecordRepository.findById(id).orElse(null);

        if (borrowRecord != null) {
            if(!authService.canAccessUser(authentication, borrowRecord.getUser().getId())) {
                return null;
            }
        }

        return borrowRecord;
    }

    public BorrowRecord updateBorrowRecord(Long id, BorrowRecord updatedBorrowRecord) {
        BorrowRecord existingBorrowRecord = borrowRecordRepository.findById(id).orElse(null);
        if (existingBorrowRecord != null) {
            existingBorrowRecord.setUser(updatedBorrowRecord.getUser());
            existingBorrowRecord.setBook(updatedBorrowRecord.getBook());
            existingBorrowRecord.setBorrowDate(updatedBorrowRecord.getBorrowDate());
            existingBorrowRecord.setReturnDate(updatedBorrowRecord.getReturnDate());

            return borrowRecordRepository.save(existingBorrowRecord);
        }
        return null;
    }

    public void deleteBorrowRecord(Long id) {
        borrowRecordRepository.deleteById(id);
    }

    public List<BorrowRecord> getAllBorrowRecords() {
        return borrowRecordRepository.findAll();
    }

    public List<BorrowRecord> getBorrowRecordsByUserId(Long userId, Authentication authentication) {

            if(!authService.canAccessUser(authentication, userId)) {
                return null;
            }
        return borrowRecordRepository.findByUserId(userId);
    }

    public List<BorrowRecord> getBorrowRecordsByBookId(Long bookId) {
        return borrowRecordRepository.findByBookId(bookId);
    }

    public List<BorrowRecord> getBorrowRecordsByUserIdAndBookId(Long userId, Long bookId, Authentication authentication) {

        if(!authService.canAccessUser(authentication, userId)) {
            return null;
        }
        
        return borrowRecordRepository.findByUserIdAndBookId(userId, bookId);
    }

    public boolean setReturnDate(Long borrowRecordId, LocalDate returnDate) {
        BorrowRecord borrowRecord = borrowRecordRepository.findById(borrowRecordId).orElse(null);
        if (borrowRecord != null) {
            borrowRecordRepository.setReturnDate(borrowRecordId, returnDate);
            return true;
        }
        return false;
    }
}
