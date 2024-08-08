package com.example.student_portal.repository;


import com.example.student_portal.dao.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SemesterRepository extends JpaRepository<Semester, String> {

    Semester findBySemesterNo(String semesterNo);
}
