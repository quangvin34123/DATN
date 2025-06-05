package com.poly.thuviendatn.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "LoaiSach")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoaiSach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maLoaiSach;

    @Column(nullable = false, length = 50)
    private String tenLoaiSach;

    @OneToMany(mappedBy = "loaiSach")
    private List<DanhMuc> danhMucs;
}