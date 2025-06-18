package com.visitormanagement.controller;

import com.visitormanagement.entity.Visitor;
import com.visitormanagement.payload.VisitorDto;
import com.visitormanagement.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/visitors")
public class VisitorController {
    @Autowired
    private VisitorService visitorService;

    public VisitorController(VisitorService visitorService) {
        this.visitorService = visitorService;
    }

    @PostMapping("/register")
    public ResponseEntity<Visitor> register(
            @RequestBody VisitorDto visitorDto
            ){
        return new ResponseEntity<>(visitorService.registerVisitor(visitorDto), HttpStatus.CREATED);
    }

    @GetMapping("/getallusers")
    public ResponseEntity<List<Visitor>> getAll(){
        return new ResponseEntity<>(visitorService.getAllVisitors(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Visitor> getById(
            @PathVariable Long id
    ){
        return new ResponseEntity<>(visitorService.getVisitorById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Visitor> updateVisitor(
            @PathVariable Long id, @RequestBody VisitorDto visitorDto
    ){
        return new ResponseEntity<>(visitorService.updateVisitor(id, visitorDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(
            @PathVariable Long id
    ){
        visitorService.deleteVisitor(id);
        return new ResponseEntity<>("Deleted Successfully", HttpStatus.ACCEPTED);
    }
}
