package com.poly.thuviendatn.Controller;

import com.poly.thuviendatn.Model.*;
import com.poly.thuviendatn.Repository.*;
import com.poly.thuviendatn.Service.SachService;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminDashboardController {

    private final TaiKhoanRepository taiKhoanRepository;
    private final SachRepository sachRepository;
    private final DanhMucRepository danhMucRepository;
    private final TacGiaRepository tacGiaRepository;
    private final NhaXuatBanRepository nhaXuatBanRepository;
    private final QuyenRepository quyenRepository;
    private final LoaiSachRepository loaiSachRepository;
    private final DocGiaRepository docGiaRepository;
    private final SachService sachService;

    @Autowired
    public AdminDashboardController(
            TaiKhoanRepository taiKhoanRepository,
            SachRepository sachRepository,
            DanhMucRepository danhMucRepository,
            TacGiaRepository tacGiaRepository,
            NhaXuatBanRepository nhaXuatBanRepository,
            QuyenRepository quyenRepository,
            LoaiSachRepository loaiSachRepository,
            DocGiaRepository docGiaRepository,
            SachService sachService) {
        this.taiKhoanRepository = taiKhoanRepository;
        this.sachRepository = sachRepository;
        this.danhMucRepository = danhMucRepository;
        this.tacGiaRepository = tacGiaRepository;
        this.nhaXuatBanRepository = nhaXuatBanRepository;
        this.quyenRepository = quyenRepository;
        this.loaiSachRepository = loaiSachRepository;
        this.docGiaRepository = docGiaRepository;
        this.sachService = sachService;
    }

    // === Độc giả Management ===
    @GetMapping("/docgia")
    public String showDocGiaList(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(required = false) String keyword,
            Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<DocGia> docGiaPage;
        if (keyword != null && !keyword.isEmpty()) {
            docGiaPage = docGiaRepository.findByTenDocGiaContainingIgnoreCaseOrCccdContainingIgnoreCase(keyword, keyword, pageable);
        } else {
            docGiaPage = docGiaRepository.findAll(pageable);
        }
        model.addAttribute("docGiaPage", docGiaPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", size);
        model.addAttribute("keyword", keyword);
        model.addAttribute("activeSection", "docgia");
        return "admin/docgia/docgia";
    }

    @GetMapping("/docgia/them")
    public String showAddDocGiaForm(Model model) {
        model.addAttribute("docGia", new DocGia());
        model.addAttribute("activeSection", "docgia");
        return "admin/docgia/themdocgia";
    }

    @PostMapping("/docgia/them")
    public String addDocGia(
            @Valid @ModelAttribute("docGia") DocGia docGia,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "admin/docgia/themdocgia";
        }
        docGiaRepository.save(docGia);
        redirectAttributes.addFlashAttribute("successMessage", "Thêm độc giả thành công!");
        return "redirect:/admin/docgia";
    }

    @GetMapping("/docgia/sua")
    public String showEditDocGiaForm(@RequestParam("id") Integer id, Model model) {
        DocGia docGia = docGiaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Độc giả không tồn tại: " + id));
        model.addAttribute("docGia", docGia);
        model.addAttribute("activeSection", "docgia");
        return "admin/docgia/themdocgia";
    }

    @GetMapping("/docgia/xoa")
    public String deleteDocGia(@RequestParam("id") Integer id, RedirectAttributes redirectAttributes) {
        docGiaRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("successMessage", "Xóa độc giả thành công!");
        return "redirect:/admin/docgia";
    }

    // === Đối tác Management ===
    @GetMapping("/doitac")
    public String showDoiTacList(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(required = false) String keyword,
            Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<NhaXuatBan> doiTacPage;
        if (keyword != null && !keyword.isEmpty()) {
            doiTacPage = nhaXuatBanRepository.findByTenNXBContainingIgnoreCase(keyword, pageable);
        } else {
            doiTacPage = nhaXuatBanRepository.findAll(pageable);
        }
        model.addAttribute("doiTacPage", doiTacPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", size);
        model.addAttribute("keyword", keyword);
        model.addAttribute("activeSection", "doitac");
        return "admin/doitac/doitac";
    }

    @GetMapping("/doitac/them")
    public String showAddDoiTacForm(Model model) {
        model.addAttribute("doiTac", new NhaXuatBan());
        model.addAttribute("activeSection", "doitac");
        return "admin/doitac/themdoitac";
    }

    @PostMapping("/doitac/them")
    public String addDoiTac(
            @Valid @ModelAttribute("doiTac") NhaXuatBan nhaXuatBan,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "admin/doitac/themdoitac";
        }
        nhaXuatBanRepository.save(nhaXuatBan);
        redirectAttributes.addFlashAttribute("successMessage", "Thêm đối tác thành công!");
        return "redirect:/admin/doitac";
    }

    @GetMapping("/doitac/sua")
    public String showEditDoiTacForm(@RequestParam("id") Integer id, Model model) {
        NhaXuatBan nhaXuatBan = nhaXuatBanRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Đối tác không tồn tại: " + id));
        model.addAttribute("doiTac", nhaXuatBan);
        model.addAttribute("activeSection", "doitac");
        return "admin/doitac/themdoitac";
    }

    @GetMapping("/doitac/xoa")
    public String deleteDoiTac(@RequestParam("id") Integer id, RedirectAttributes redirectAttributes) {
        nhaXuatBanRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("successMessage", "Xóa đối tác thành công!");
        return "redirect:/admin/doitac";
    }

    // === Lịch sử Management ===
    @GetMapping("/lichsu")
    public String showLichSuList(Model model) {
        model.addAttribute("activeSection", "lichsu");
        return "admin/lichsu/lichsu";
    }

    // === Phiếu mượn Management ===
    @GetMapping("/nhapmuon")
    public String showPhieuMuonManagement(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(required = false) String keyword,
            Model model) {
        // Assuming ChiTietPhieuMuonRepository exists (add it if needed)
        // Placeholder: Replace with actual repository
        Pageable pageable = PageRequest.of(page, size);
        // Page<ChiTietPhieuMuon> phieuMuonPage = chiTietPhieuMuonRepository.findBySomeField(keyword, pageable);
        // model.addAttribute("phieuMuonPage", phieuMuonPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", size);
        model.addAttribute("keyword", keyword);
        model.addAttribute("activeSection", "nhapmuon");
        return "admin/nhapmuon/nhapmuon";
    }

    @GetMapping("/nhapmuon/them")
    public String showAddPhieuMuonForm(Model model) {
        model.addAttribute("phieuMuon", new ChiTietPhieuMuon()); // Assuming ChiTietPhieuMuon entity
        model.addAttribute("activeSection", "nhapmuon");
        return "admin/nhapmuon/themphieumuon";
    }

    @PostMapping("/nhapmuon/them")
    public String addPhieuMuon(
            @Valid @ModelAttribute("phieuMuon") ChiTietPhieuMuon phieuMuon,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "admin/nhapmuon/themphieumuon";
        }
        // Assuming ChiTietPhieuMuonRepository (add it if needed)
        // chiTietPhieuMuonRepository.save(phieuMuon);
        redirectAttributes.addFlashAttribute("successMessage", "Thêm phiếu mượn thành công!");
        return "redirect:/admin/nhapmuon";
    }

    @GetMapping("/nhapmuon/sua")
    public String showEditPhieuMuonForm(@RequestParam("id") Integer id, Model model) {
        // Assuming ChiTietPhieuMuonRepository
        // ChiTietPhieuMuon phieuMuon = chiTietPhieuMuonRepository.findById(id)
        //         .orElseThrow(() -> new IllegalArgumentException("Phiếu mượn không tồn tại: " + id));
        // model.addAttribute("phieuMuon", phieuMuon);
        model.addAttribute("activeSection", "nhapmuon");
        return "admin/nhapmuon/themphieumuon";
    }

    @GetMapping("/nhapmuon/xoa")
    public String deletePhieuMuon(@RequestParam("id") Integer id, RedirectAttributes redirectAttributes) {
        // Assuming ChiTietPhieuMuonRepository
        // chiTietPhieuMuonRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("successMessage", "Xóa phiếu mượn thành công!");
        return "redirect:/admin/nhapmuon";
    }

@GetMapping("/quanlysach")
    public String showQuanLySach(
            @RequestParam(defaultValue = "0") int sachPage,
            @RequestParam(defaultValue = "10") int sachSize,
            @RequestParam(required = false) String sachKeyword,
            @RequestParam(defaultValue = "0") int loaiSachPage,
            @RequestParam(defaultValue = "10") int loaiSachSize,
            @RequestParam(required = false) String loaiSachKeyword,
            @RequestParam(defaultValue = "0") int danhMucPage,
            @RequestParam(defaultValue = "10") int danhMucSize,
            @RequestParam(required = false) String danhMucKeyword,
            Model model) {
        Page<Sach> sachPageResult = sachService.findAllBooks(sachPage, sachSize, sachKeyword);
        model.addAttribute("sachPage", sachPageResult);
        model.addAttribute("sachKeyword", sachKeyword);

        Pageable loaiSachPageable = PageRequest.of(loaiSachPage, loaiSachSize);
        Page<LoaiSach> loaiSachPageResult = loaiSachRepository.findByTenLoaiSachContainingIgnoreCase(loaiSachKeyword != null ? loaiSachKeyword : "", loaiSachPageable);
        model.addAttribute("loaiSachPage", loaiSachPageResult);
        model.addAttribute("loaiSachKeyword", loaiSachKeyword);

        Pageable danhMucPageable = PageRequest.of(danhMucPage, danhMucSize);
        Page<DanhMuc> danhMucPageResult = danhMucRepository.findByTenDanhMucContainingIgnoreCase(danhMucKeyword != null ? danhMucKeyword : "", danhMucPageable);
        model.addAttribute("danhMucPage", danhMucPageResult);
        model.addAttribute("danhMucKeyword", danhMucKeyword);

        model.addAttribute("activeSection", "quanlysach");
        return "admin/quanlysach/quanlysach";
    }

    @GetMapping("/quanlysach/them-sach")
    public String showAddSachForm(Model model) {
        model.addAttribute("sach", new Sach());
        model.addAttribute("loaiSachs", loaiSachRepository.findAll());
        model.addAttribute("tacGias", tacGiaRepository.findAll());
        model.addAttribute("nhaXuatBans", nhaXuatBanRepository.findAll());
        model.addAttribute("activeSection", "quanlysach");
        return "admin/quanlysach/themsach";
    }

    @PostMapping("/quanlysach/them-sach")
    public String addSach(
            @Valid @ModelAttribute("sach") Sach sach,
            BindingResult result,
            @RequestParam("hinhAnh") MultipartFile coverImage,
            @RequestParam("trangSachs[0].hinhAnhs") List<MultipartFile> pageImages,
            @RequestParam("loaiSach.maLoaiSach") Integer maLoaiSach,
            Model model,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("loaiSachs", loaiSachRepository.findAll());
            model.addAttribute("tacGias", tacGiaRepository.findAll());
            model.addAttribute("nhaXuatBans", nhaXuatBanRepository.findAll());
            return "admin/quanlysach/themsach";
        }

        // Validate DanhMuc belongs to LoaiSach
        DanhMuc danhMuc = danhMucRepository.findById(sach.getDanhMuc().getMaDanhMuc())
                .orElseThrow(() -> new IllegalArgumentException("Danh mục không tồn tại"));
        if (!danhMuc.getLoaiSach().getMaLoaiSach().equals(maLoaiSach)) {
            result.rejectValue("danhMuc.maDanhMuc", "error.danhMuc", "Danh mục không thuộc loại sách đã chọn!");
            model.addAttribute("loaiSachs", loaiSachRepository.findAll());
            model.addAttribute("tacGias", tacGiaRepository.findAll());
            model.addAttribute("nhaXuatBans", nhaXuatBanRepository.findAll());
            return "admin/quanlysach/themsach";
        }

        try {
            sachService.saveSachWithPages(sach, coverImage, pageImages);
            redirectAttributes.addFlashAttribute("successMessage", "Thêm sách thành công!");
            return "redirect:/admin/quanlysach";
        } catch (Exception e) {
            model.addAttribute("loaiSachs", loaiSachRepository.findAll());
            model.addAttribute("tacGias", tacGiaRepository.findAll());
            model.addAttribute("nhaXuatBans", nhaXuatBanRepository.findAll());
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi khi thêm sách: " + e.getMessage());
            return "admin/quanlysach/themsach";
        }
    }

    @GetMapping("/quanlysach/sua-sach")
    public String showEditSachForm(@RequestParam("id") Integer id, Model model) {
        Sach sach = sachService.findById(id);
        if (sach == null) {
            throw new IllegalArgumentException("Sách không tồn tại: " + id);
        }
        model.addAttribute("sach", sach);
        model.addAttribute("loaiSachs", loaiSachRepository.findAll());
        model.addAttribute("tacGias", tacGiaRepository.findAll());
        model.addAttribute("nhaXuatBans", nhaXuatBanRepository.findAll());
        model.addAttribute("selectedLoaiSach", sach.getDanhMuc().getLoaiSach().getMaLoaiSach());
        model.addAttribute("activeSection", "quanlysach");
        return "admin/quanlysach/themsach";
    }

    @GetMapping("/quanlysach/xoa-sach")
    public String deleteSach(@RequestParam("id") Integer id, RedirectAttributes redirectAttributes) {
        sachRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("successMessage", "Xóa sách thành công!");
        return "redirect:/admin/quanlysach";
    }

    @GetMapping("/quanlysach/danhmuc-by-loaisach")
    public ResponseEntity<List<DanhMuc>> getDanhMucByLoaiSach(@RequestParam("maLoaiSach") Integer maLoaiSach) {
        List<DanhMuc> danhMucs = danhMucRepository.findByLoaiSachMaLoaiSach(maLoaiSach);
        return ResponseEntity.ok(danhMucs);
    }

    @GetMapping("/quanlysach/them-loai-sach")
    public String showAddLoaiSachForm(Model model) {
        model.addAttribute("loaiSach", new LoaiSach());
        model.addAttribute("activeSection", "quanlysach");
        return "admin/quanlysach/themloaisach";
    }

    @PostMapping("/quanlysach/them-loai-sach")
    public String addLoaiSach(
            @Valid @ModelAttribute("loaiSach") LoaiSach loaiSach,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "admin/quanlysach/themloaisach";
        }
        loaiSachRepository.save(loaiSach);
        redirectAttributes.addFlashAttribute("successMessage", "Thêm loại sách thành công!");
        return "redirect:/admin/quanlysach";
    }

    @GetMapping("/quanlysach/sua-loai-sach")
    public String showEditLoaiSachForm(@RequestParam("id") Integer id, Model model) {
        LoaiSach loaiSach = loaiSachRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Loại sách không tồn tại: " + id));
        model.addAttribute("loaiSach", loaiSach);
        model.addAttribute("activeSection", "quanlysach");
        return "admin/quanlysach/themloaisach";
    }

    @GetMapping("/quanlysach/xoa-loai-sach")
    public String deleteLoaiSach(@RequestParam("id") Integer id, RedirectAttributes redirectAttributes) {
        loaiSachRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("successMessage", "Xóa loại sách thành công!");
        return "redirect:/admin/quanlysach";
    }

    @GetMapping("/quanlysach/them-danhmuc")
    public String showAddDanhMucForm(Model model) {
        model.addAttribute("danhMuc", new DanhMuc());
        model.addAttribute("loaiSachs", loaiSachRepository.findAll());
        model.addAttribute("activeSection", "quanlysach");
        return "admin/quanlysach/themdanhmuc";
    }

    @PostMapping("/quanlysach/them-danhmuc")
    public String addDanhMuc(
            @Valid @ModelAttribute("danhMuc") DanhMuc danhMuc,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("loaiSachs", loaiSachRepository.findAll());
            return "admin/quanlysach/themdanhmuc";
        }
        danhMucRepository.save(danhMuc);
        redirectAttributes.addFlashAttribute("successMessage", "Thêm danh mục thành công!");
        return "redirect:/admin/quanlysach";
    }

    @GetMapping("/quanlysach/sua-danhmuc")
    public String showEditDanhMucForm(@RequestParam("id") Integer id, Model model) {
        DanhMuc danhMuc = danhMucRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Danh mục không tồn tại: " + id));
        model.addAttribute("danhMuc", danhMuc);
        model.addAttribute("loaiSachs", loaiSachRepository.findAll());
        model.addAttribute("activeSection", "quanlysach");
        return "admin/quanlysach/themdanhmuc";
    }

    @GetMapping("/quanlysach/xoa-danhmuc")
    public String deleteDanhMuc(@RequestParam("id") Integer id, RedirectAttributes redirectAttributes) {
        danhMucRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("successMessage", "Xóa danh mục thành công!");
        return "redirect:/admin/quanlysach";
    }

    // === Thông báo Management ===
    @GetMapping("/thongbao")
    public String showThongBaoList(Model model) {
        model.addAttribute("activeSection", "thongbao");
        return "admin/thongbao/thongbao";
    }

    // === Thống kê Management ===
    @GetMapping("/thongke")
    public String showThongKeList(Model model) {
        model.addAttribute("activeSection", "thongke");
        return "admin/thongke/thongke";
    }

    // === Đánh giá Management ===
    @GetMapping("/danhgia")
    public String showDanhGiaList(Model model) {
        model.addAttribute("activeSection", "danhgia");
        return "admin/danhgia/danhgia";
    }
}