package com.poly.thuviendatn.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poly.thuviendatn.Model.Feedback;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}

