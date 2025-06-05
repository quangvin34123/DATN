package com.poly.thuviendatn.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "TrangSach")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrangSach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maTrang;

    @ManyToOne
    @JoinColumn(name = "MaSach", nullable = false)
    private Sach sach;

    // Xóa bỏ soTrang và noiDung vì không cần nữa

    // Danh sách ảnh của trang sách
    @ElementCollection
    @CollectionTable(name = "TrangSach_HinhAnh", joinColumns = @JoinColumn(name = "MaTrang"))
    @Column(name = "HinhAnh", length = 255, nullable = false)
    private List<String> hinhAnhs;
}
