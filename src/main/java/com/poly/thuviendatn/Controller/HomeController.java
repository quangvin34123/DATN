
package com.poly.thuviendatn.Controller;

import com.poly.thuviendatn.Model.LoaiSach;
import com.poly.thuviendatn.Model.Sach;
import com.poly.thuviendatn.Repository.LoaiSachRepository;
import com.poly.thuviendatn.Repository.SachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {

    private final SachRepository sachRepository;
    private final LoaiSachRepository loaiSachRepository;

    @Autowired
    public HomeController(SachRepository sachRepository, LoaiSachRepository loaiSachRepository) {
        this.sachRepository = sachRepository;
        this.loaiSachRepository = loaiSachRepository;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("sachList", sachRepository.findAll());
        return "public/home";
    }
@GetMapping("/books")
public String booksByCategory(@RequestParam("categoryId") Integer categoryId, Model model) {
    Optional<LoaiSach> loaiSach = loaiSachRepository.findById(categoryId);
    List<Sach> sachList = sachRepository.findByDanhMucLoaiSachMaCategory(categoryId);
    
    // Add logging to debug hinhAnh
    sachList.forEach(sach -> System.out.println("Book ID: " + sach.getMaSach() + ", hinhAnh: " + sach.getHinhAnh()));
    
    model.addAttribute("loaiSach", loaiSach.orElse(null));
    model.addAttribute("sachList", sachList);
    return "public/sachtheodanhmuc";
}

    @GetMapping("/aboutus")
    public String showGioiThieu(Model model) {
        model.addAttribute("sachList", sachRepository.findAll());
        return "layout/gioithieu";
    }


}
