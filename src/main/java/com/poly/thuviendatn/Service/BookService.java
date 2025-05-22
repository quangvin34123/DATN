package com.poly.thuviendatn.Service;

import com.poly.thuviendatn.Model.Book;
import com.poly.thuviendatn.Repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;

    // Constructor injection
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Lấy danh sách tất cả sách
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Tìm sách theo id, trả về null nếu không tìm thấy
    public Book findById(Integer id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.orElse(null);
    }
}
