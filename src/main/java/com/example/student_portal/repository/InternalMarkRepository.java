package com.example.student_portal.repository;

import com.example.student_portal.dao.InternalMark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InternalMarkRepository extends JpaRepository<InternalMark, String > {
    Optional<InternalMark> findByUserIdAndSubjectIdAndSubject_Semester_Id(String userId, String subjectId, String semesterId);
}
