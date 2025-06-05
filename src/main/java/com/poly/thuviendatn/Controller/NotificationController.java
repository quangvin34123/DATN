package com.poly.thuviendatn.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NotificationController { // Renamed class for clarity (optional but recommended)
    @GetMapping("/notification") // Changed from "/notifition"
    public String thongBao(Model model) {
        return "Layout/notifition"; // Update view name to match (correct typo)
    }
}