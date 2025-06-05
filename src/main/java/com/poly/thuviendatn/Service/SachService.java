package com.poly.thuviendatn.Service;

import com.poly.thuviendatn.Model.DanhGia;
import com.poly.thuviendatn.Model.LoaiSach;
import com.poly.thuviendatn.Model.Sach;
import com.poly.thuviendatn.Model.TrangSach;
import com.poly.thuviendatn.Repository.DanhGiaRepository;
import com.poly.thuviendatn.Repository.LoaiSachRepository;
import com.poly.thuviendatn.Repository.SachRepository;
import com.poly.thuviendatn.Repository.TrangSachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SachService implements ISachService {

    private final SachRepository sachRepository;
    private final DanhGiaRepository danhGiaRepository;
    private final LoaiSachRepository loaiSachRepository;
    private final TrangSachRepository trangSachRepository;

    @Autowired
    public SachService(SachRepository sachRepository, DanhGiaRepository danhGiaRepository,
                       LoaiSachRepository loaiSachRepository, TrangSachRepository trangSachRepository) {
        this.sachRepository = sachRepository;
        this.danhGiaRepository = danhGiaRepository;
        this.loaiSachRepository = loaiSachRepository;
        this.trangSachRepository = trangSachRepository;
    }

    // Lấy danh sách sách có phân trang và tìm kiếm
    @Override
    public Page<Sach> findAllBooks(int page, int size, String keyword) {
        Pageable pageable = PageRequest.of(page, size);
        if (keyword != null && !keyword.isEmpty()) {
            return sachRepository.findByTenSachContainingIgnoreCase(keyword, pageable);
        }
        return sachRepository.findAll(pageable);
    }

    // Lấy danh sách tất cả sách (không phân trang)
    @Override
    public List<Sach> getAllBooks() {
        return sachRepository.findAll();
    }

    // Lấy danh sách tất cả loại sách
    @Override
    public List<LoaiSach> getAllLoaiSach() {
        return loaiSachRepository.findAll();
    }

    // Lấy danh sách sách theo mã loại sách
    @Override
    public List<Sach> getBooksByLoaiSachId(Integer maCategory) {
        return sachRepository.findByDanhMucLoaiSachMaCategory(maCategory);
    }

    // Tìm sách theo id, trả về null nếu không tìm thấy
    @Override
    public Sach findById(Integer id) {
        Optional<Sach> sach = sachRepository.findById(id);
        return sach.orElse(null);
    }

    // Lưu đánh giá
    @Override
    public void saveDanhGia(DanhGia danhGia) {
        danhGiaRepository.save(danhGia);
    }

    // Lấy danh sách đánh giá theo mã sách
    @Override
    public List<DanhGia> getDanhGiasBySachId(Integer maSach) {
        return danhGiaRepository.findBySachMaSach(maSach);
    }

    // Lưu sách với hình ảnh bìa và các trang sách
    @Override
    public void saveSachWithPages(Sach sach, MultipartFile coverImage, List<MultipartFile> pageImages) throws IOException {
        // Save cover image
        if (coverImage != null && !coverImage.isEmpty()) {
            String coverImagePath = saveFile(coverImage);
            sach.setHinhAnh(coverImagePath);
        }

        // Save the book
        Sach savedSach = sachRepository.save(sach);

        // Save page images
        if (pageImages != null && !pageImages.isEmpty()) {
            List<String> pageImagePaths = new ArrayList<>();
            for (MultipartFile pageImage : pageImages) {
                if (pageImage != null && !pageImage.isEmpty()) {
                    String pageImagePath = saveFile(pageImage);
                    pageImagePaths.add(pageImagePath);
                }
            }

            TrangSach trangSach = new TrangSach();
            trangSach.setSach(savedSach);
            trangSach.setHinhAnhs(pageImagePaths);
            trangSachRepository.save(trangSach);
        }
    }

    // Helper method to save uploaded files
    private String saveFile(MultipartFile file) throws IOException {
        String uploadDir = "Uploads/";
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Path filePath = uploadPath.resolve(fileName);
        Files.write(filePath, file.getBytes());

        return uploadDir + fileName;
    }
}