package com.poly.thuviendatn.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.thuviendatn.Model.Book;
import com.poly.thuviendatn.Service.BookService;

@Controller
@RequestMapping("/product")
public class ProductController {

    // Inject BookService using constructor injection (preferred in Spring)
    private final BookService bookService;

    public ProductController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/bookintro")
    public String showBookDetail() {
        return "Layout/bookintro"; // Thymeleaf sẽ tìm tệp login.html trong thư mục templates/Account/
    }

    @GetMapping("/{id}")
    public String showBookDetail(@PathVariable("id") Integer id, Model model) {
        // Use the injected bookService instance to call findById
        Book book = bookService.findById(id);
        if (book == null) {
            return "error/404"; // hoặc trang báo lỗi không tìm thấy sách
        }
        model.addAttribute("book", book);
        return "Public/bookdetail"; // Đường dẫn file Thymeleaf: templates/Product/book-detail.html
    }
}