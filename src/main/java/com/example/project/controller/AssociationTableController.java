package com.example.project.controller;

import com.example.project.model.AssociationTable;
import com.example.project.service.AssociationTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AssociationTableController {

    @Autowired
    private AssociationTableService associationTableService;

    @GetMapping("/associations")
    List<AssociationTable> getAssociationTable(){
        return associationTableService.getAssociationTable();
    }

}
