package com.poly.thuviendatn.Controller;

import com.poly.thuviendatn.Model.Borrowing;
import com.poly.thuviendatn.Repository.BorrowingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Controller
public class PaymentController {

    private final BorrowingRepository borrowingRepository;

    @Autowired
    public PaymentController(BorrowingRepository borrowingRepository) {
        this.borrowingRepository = borrowingRepository;
    }

    @GetMapping("/payment")
    public String showPaymentPage(Model model) {
        // Fetch all borrowings from the database
        List<Borrowing> borrowedBooks = borrowingRepository.findAll();

        // Calculate overdue status and fines as of May 19, 2025
        LocalDate today = LocalDate.of(2025, 5, 19);
        for (Borrowing borrowing : borrowedBooks) {
            if (borrowing.getDueDate().isBefore(today) && borrowing.getReturnDate() == null) {
                borrowing.setStatus("overdue");
                long daysOverdue = java.time.temporal.ChronoUnit.DAYS.between(borrowing.getDueDate(), today);
                borrowing.setFine(BigDecimal.valueOf(daysOverdue * 10000)); // 10,000 VNĐ per day
            } else if (borrowing.getReturnDate() == null) {
                borrowing.setStatus("borrowed");
            } else {
                borrowing.setStatus("returned");
            }
        }

        // Mock bank payment URL
        String bankPaymentUrl = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html?vnp_Amount=50000&vnp_ReturnUrl=http://localhost:8080/payment/return";

        // Add data to model
        model.addAttribute("borrowedBooks", borrowedBooks);
        model.addAttribute("bankPaymentUrl", bankPaymentUrl);

        return "Payment/paymen"; // Maps to payment.html
    }

    @GetMapping("/payment/success")
    public String paymentSuccess() {
        return "Payment/payment-success";
    }

    @GetMapping("/payment/fail")
    public String paymentFail() {
        return "Payment/payment-failed";
    }
}
