package com.example.project.repository;


import com.example.project.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Query(value = "SELECT * FROM demo.payment WHERE user_id=?1", nativeQuery = true)
    List<Payment> findByUserId(Long id);
}
