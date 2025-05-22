package com.poly.thuviendatn.Repository;

import com.poly.thuviendatn.Model.BookPage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookPageRepository extends JpaRepository<BookPage, Integer> {
    List<BookPage> findByBookId(Integer bookId);
}