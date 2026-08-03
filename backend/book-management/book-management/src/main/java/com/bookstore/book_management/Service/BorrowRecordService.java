/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.bookstore.book_management.Service;

/**
 *
 * @author Admin
 */
import org.springframework.stereotype.Service;
import com.bookstore.book_management.Repository.BorrowRecordRepository;
import com.bookstore.book_management.Entity.BorrowRecord;
import java.util.List;
import java.time.LocalDate;

@Service
public class BorrowRecordService {
    private final BorrowRecordRepository borrowRecordRepository;

    public BorrowRecordService(BorrowRecordRepository borrowRecordRepository) {
        this.borrowRecordRepository = borrowRecordRepository;
    }

    public BorrowRecord createBorrowRecord(BorrowRecord borrowRecord) {
        return borrowRecordRepository.save(borrowRecord);
    }

    public BorrowRecord getBorrowRecordById(Long id) {
        return borrowRecordRepository.findById(id).orElse(null);
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

    public List<BorrowRecord> getBorrowRecordsByUserId(Long userId) {
        return borrowRecordRepository.findByUserId(userId);
    }

    public List<BorrowRecord> getBorrowRecordsByBookId(Long bookId) {
        return borrowRecordRepository.findByBookId(bookId);
    }

    public List<BorrowRecord> getBorrowRecordsByUserIdAndBookId(Long userId, Long bookId) {
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
