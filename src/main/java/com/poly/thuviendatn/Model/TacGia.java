package com.poly.thuviendatn.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "TacGia")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TacGia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maTacGia;

    @Column(length = 100)
    private String tenTacGia;

    @Temporal(TemporalType.DATE)
    private Date namSinh;

    @Column(length = 100)
    private String queQuan;

    @OneToMany(mappedBy = "tacGia")
    private List<Sach> sachs;
}