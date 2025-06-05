package com.poly.thuviendatn.Repository;

import com.poly.thuviendatn.Model.PhieuMuon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhieuMuonRepository extends JpaRepository<PhieuMuon, Integer> {
}