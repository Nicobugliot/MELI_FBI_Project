package com.example.project.repository;

import com.example.project.model.Charge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface ChargeRepository extends JpaRepository<Charge, Long> {

    List<Charge> findByUserId(Long id);

    @Query(value = "SELECT * FROM demo.event WHERE user_id=?1 AND MONTH(date)=?2 AND YEAR(date)=?3", nativeQuery = true)
    List<Charge> findByUserIdMonthAndYear(Long id, Integer month, Integer year);

    @Query(value = "SELECT * FROM demo.event WHERE user_id=?1 AND MONTH(date)=?2 AND YEAR(date)=?3 AND paid_out=0", nativeQuery = true)
    public List<Charge> findByUserIdMonthAndYearNotPaid(Long id, Integer month, Integer year);

    @Query(value = "SELECT * FROM demo.event WHERE user_id=?1 AND paid_out=0", nativeQuery = true)
    List<Charge> findChargesByUserIdNotPaid(Long id);

    @Query(value = "SELECT * FROM demo.event WHERE event_id=?1", nativeQuery = true)
    Charge findByEventId(Long id);
}
