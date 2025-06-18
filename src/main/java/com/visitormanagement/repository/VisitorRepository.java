package com.visitormanagement.repository;

import com.visitormanagement.entity.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitorRepository extends JpaRepository<Visitor, Long> {
    public boolean existsByEmail(String email);
    public boolean existsByContact(String contact);
}