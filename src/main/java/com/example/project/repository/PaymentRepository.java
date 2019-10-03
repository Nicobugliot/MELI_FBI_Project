package com.example.project.repository;


import com.example.project.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findByUserId(Long id);

    @Query(value = "SELECT * FROM demo.payment WHERE user_id=?1 AND MONTH(date)=?2 AND YEAR(date)=?3", nativeQuery = true)
    List<Payment> findPaymentByUserMonthAndYear(Long id, Integer month, Integer year);
}
