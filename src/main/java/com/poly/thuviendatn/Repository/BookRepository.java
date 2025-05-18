package com.poly.thuviendatn.Repository;

import com.poly.thuviendatn.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Đảm bảo thêm annotation này
public interface BookRepository extends JpaRepository<Book, Integer> {
}