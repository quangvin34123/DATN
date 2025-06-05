package com.poly.thuviendatn.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "NhaXuatBan")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NhaXuatBan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maNXB;

    @Column(length = 100)
    private String tenNXB;

    @Column(length = 200)
    private String diaChi;

    @Column(length = 15)
    private String sdt;

    @OneToMany(mappedBy = "nhaXuatBan")
    private List<Sach> sachs;
}