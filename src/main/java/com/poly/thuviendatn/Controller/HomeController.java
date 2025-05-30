package com.poly.thuviendatn.Controller;

import com.poly.thuviendatn.Service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final BookService bookService;

    public HomeController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "Public/home";
    }

        @GetMapping("/aboutus")
    public String gioithieu(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "Layout/gioithieu";
    }
}