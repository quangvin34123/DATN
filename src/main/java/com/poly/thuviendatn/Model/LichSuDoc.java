package com.poly.thuviendatn.Model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "LichSuDoc")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LichSuDoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maLichSu;

    @ManyToOne
    @JoinColumn(name = "maDocGia")
    private DocGia docGia;

    @ManyToOne
    @JoinColumn(name = "maSach")
    private Sach sach;

    @Column
    private Integer trangCuoi;

    @Column
    private LocalDateTime thoiGianDoc;
}