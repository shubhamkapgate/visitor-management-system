package com.visitormanagement.service;

import com.visitormanagement.entity.Visitor;
import com.visitormanagement.payload.VisitorDto;

import java.util.List;

public interface VisitorService {
    public Visitor registerVisitor(VisitorDto visitorDto);
    public List<Visitor> getAllVisitors();
    public Visitor getVisitorById(Long id);
    public Visitor updateVisitor(Long id, VisitorDto visitorDto);
    public void deleteVisitor(Long id);
}
