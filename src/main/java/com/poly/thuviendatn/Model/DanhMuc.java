package com.poly.thuviendatn.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "DanhMuc")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DanhMuc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maDanhMuc;

    @Column(nullable = false, length = 100)
    private String tenDanhMuc;

    @ManyToOne
    @JoinColumn(name = "maLoaiSach", nullable = false)
    private LoaiSach loaiSach;

    @Column
    private Integer parentId;

    @OneToMany(mappedBy = "danhMuc")
    private List<Sach> sachs;
}