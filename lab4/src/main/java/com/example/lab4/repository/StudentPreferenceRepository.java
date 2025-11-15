package com.example.lab4.repository;

import com.example.lab4.entity.StudentPreference;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StudentPreferenceRepository extends JpaRepository<StudentPreference, Long> {
    List<StudentPreference> findByStudentId(Long studentId);
}
