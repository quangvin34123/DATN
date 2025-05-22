package com.poly.thuviendatn.Controller;

import com.poly.thuviendatn.Model.Account;
import com.poly.thuviendatn.Model.Book;
import com.poly.thuviendatn.Repository.AccountRepository;
import com.poly.thuviendatn.Repository.BookRepository;
import com.poly.thuviendatn.Repository.CategoryRepository;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminDashboardController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    // === Account ===

    @GetMapping("/useraccount")
    public String listAccounts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            Model model) {
        
        Pageable pageable = PageRequest.of(page, size);
        Page<Account> accountsPage = accountRepository.findAll(pageable);
        
        model.addAttribute("accountsPage", accountsPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", size);
        
        return "admin/account/accountadmin"; // Đường dẫn đến file HTML Thymeleaf
    }


    @GetMapping("/useraccount/add")
    public String showAddAccountForm(Model model) {
        model.addAttribute("account", new Account());
        return "Admin/account/addaccountadmin";
    }

    @PostMapping("/useraccount/add")
    public String addAccount(@Valid @ModelAttribute Account account,
                             BindingResult result,
                             RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "Admin/account/addaccountadmin";
        }

        try {
            accountRepository.save(account);
            redirectAttributes.addFlashAttribute("successMessage", "Thêm tài khoản thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Có lỗi xảy ra khi thêm tài khoản!");
        }
        return "redirect:/admin/account/useraccount";
    }


    // === Book ===

    @GetMapping("/book")
    public String showBookManagement(Model model) {
        List<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "Admin/book/bookadmin";
    }

    @GetMapping("/book/add")
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("categories", categoryRepository.findAll());
        return "Admin/book/addbookadmin";
    }

    @PostMapping("/book/add")
    public String addBook(@Valid @ModelAttribute Book book,
                          BindingResult result,
                          Model model,
                          RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("categories", categoryRepository.findAll());
            return "Admin/bookaddbookadmin";
        }

        try {
            book.setCreatedAt(LocalDateTime.now());
            book.setUpdatedAt(LocalDateTime.now());
            bookRepository.save(book);
            redirectAttributes.addFlashAttribute("successMessage", "Thêm sách thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Có lỗi xảy ra khi thêm sách!");
        }
        return "redirect:/admin/book/book";
    }

    @GetMapping("/book/edit/{id}")
    public String showEditBookForm(@PathVariable("id") Integer id,
                                   Model model,
                                   RedirectAttributes redirectAttributes) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Sách không tồn tại!");
            return "redirect:/admin/book";
        }
        model.addAttribute("book", optionalBook.get());
        model.addAttribute("categories", categoryRepository.findAll());
        return "Admin/book/editbookadmin";
    }

    @PostMapping("/book/edit/{id}")
    public String updateBook(@PathVariable("id") Integer id,
                             @Valid @ModelAttribute Book book,
                             BindingResult result,
                             Model model,
                             RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("categories", categoryRepository.findAll());
            return "Admin/book/editbookadmin";
        }

        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Sách không tồn tại!");
            return "redirect:/admin/book/book";
        }

        try {
            Book existingBook = optionalBook.get();
            existingBook.setTitle(book.getTitle());
            existingBook.setAuthor(book.getAuthor());
            existingBook.setReview(book.getReview());
            existingBook.setTotalQuantity(book.getTotalQuantity());
            existingBook.setAvailableQuantity(book.getAvailableQuantity());
            existingBook.setImage(book.getImage());
            existingBook.setRepublish(book.getRepublish());
            existingBook.setCategory(book.getCategory());
            existingBook.setUpdatedAt(LocalDateTime.now());

            bookRepository.save(existingBook);
            redirectAttributes.addFlashAttribute("successMessage", "Cập nhật sách thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Có lỗi xảy ra khi cập nhật sách!");
        }

        return "redirect:/admin/book/book";
    }

    @GetMapping("/book/delete/{id}")
    public String deleteBook(@PathVariable("id") Integer id,
                             RedirectAttributes redirectAttributes) {
        try {
            bookRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("successMessage", "Xóa sách thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Có lỗi xảy ra khi xóa sách!");
        }
        return "redirect:/admin/book/book";
    }


    // === Các phần khác ===

    @GetMapping("/borrow")
    public String showBorrowManagement() {
        return "Admin/borrow/borrowadmin";
    }

    @GetMapping("/category")
    public String showCategoryManagement() {
        return "Admin/category/categoryadmin";
    }

    @GetMapping("/copyright")
    public String showCopyrightManagement() {
        return "Admin/copyright/copyrightadmin";
    }

    @GetMapping("/staff")
    public String showStaffManagement() {
        return "Admin/staff/staffadmin";
    }

    @GetMapping("/statistics")
    public String showStatisticsManagement() {
        return "Admin/statistics/statisticsadmin";
    }

    @GetMapping("/history")
    public String showHistoryManagement() {
        return "Admin/history/historyadmin";
    }
}
