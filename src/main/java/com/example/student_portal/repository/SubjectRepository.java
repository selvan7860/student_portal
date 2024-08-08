package com.example.student_portal.repository;

import com.example.student_portal.dao.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, String> {
    Optional<Subject> findByIdAndSemesterId(String subjectId, String semesterId);
}
