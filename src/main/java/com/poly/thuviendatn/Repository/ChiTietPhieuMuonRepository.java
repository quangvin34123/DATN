package com.poly.thuviendatn.Repository;

import com.poly.thuviendatn.Model.ChiTietPhieuMuon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChiTietPhieuMuonRepository extends JpaRepository<ChiTietPhieuMuon, Integer> {
    Page<ChiTietPhieuMuon> findByMaCTPMContainingIgnoreCase(String maCTPM, Pageable pageable);
}