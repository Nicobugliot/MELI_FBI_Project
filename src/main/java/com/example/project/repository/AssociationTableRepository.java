package com.example.project.repository;

import com.example.project.model.AssociationTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface AssociationTableRepository extends JpaRepository<AssociationTable, Long> {

}
