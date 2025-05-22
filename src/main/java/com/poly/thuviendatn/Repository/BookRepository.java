package com.poly.thuviendatn.Repository;
import com.poly.thuviendatn.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    // Add custom queries if needed, e.g., find borrowings by user
}