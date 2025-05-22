package com.poly.thuviendatn.Repository;

import com.poly.thuviendatn.Model.Borrowing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowingRepository extends JpaRepository<Borrowing, Integer> {
    // Add custom queries if needed, e.g., find borrowings by user
}