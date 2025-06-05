package com.poly.thuviendatn.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "FileSach")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileSach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maFile;

    @OneToOne
    @JoinColumn(name = "maSach", nullable = false)
    private Sach sach;

    @Column(length = 255)
    private String filePath;

    @Column(length = 50)
    private String dinhDang;

    @Column
    private Long kichThuoc;
}