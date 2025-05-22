package com.poly.thuviendatn.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poly.thuviendatn.Model.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}

