package com.example.lab4.repository;

import com.example.lab4.entity.InstructorPreference;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface InstructorPreferenceRepository extends JpaRepository<InstructorPreference, Long> {
    List<InstructorPreference> findByCourseId(Long courseId);
    List<InstructorPreference> findByCompulsoryCourseAbbr(String abbr);
}
