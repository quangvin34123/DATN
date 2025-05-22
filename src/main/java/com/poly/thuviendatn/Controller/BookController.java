package com.poly.thuviendatn.Controller;

import com.poly.thuviendatn.Model.Book;
import com.poly.thuviendatn.Model.BookPage;
import com.poly.thuviendatn.Repository.BookPageRepository;
import com.poly.thuviendatn.Repository.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookPageRepository bookPageRepository;

    @GetMapping("/book/read/{id}")
    public String readBook(@PathVariable("id") Integer bookId, Model model) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid book ID"));
        List<BookPage> pages = bookPageRepository.findByBookId(bookId);

        model.addAttribute("book", book);
        model.addAttribute("pages", pages);
        return "Public/readbook";
    }
}