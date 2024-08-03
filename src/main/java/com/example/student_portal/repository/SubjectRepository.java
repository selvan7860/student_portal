package com.example.student_portal.repository;

import com.example.student_portal.dao.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, String> {
}
