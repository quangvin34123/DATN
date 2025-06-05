package com.poly.thuviendatn.Model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "NhanVien")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NhanVien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maNV;

    @Column(length = 100)
    private String tenNV;

    @Temporal(TemporalType.DATE)
    private Date namSinh;

    @Column(columnDefinition = "BIT")
    private Boolean gioiTinh;

    @Column(length = 15)
    private String sdt;

    @Temporal(TemporalType.DATE)
    private Date ngayBatDau;

    @Column(precision = 19, scale = 4)
    private BigDecimal luong;

    @Column(length = 200)
    private String diaChi;

    @OneToMany(mappedBy = "nhanVien")
    private List<PhieuMuon> phieuMuons;

}