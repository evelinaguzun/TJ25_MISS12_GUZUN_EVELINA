package com.example.lab4.repository;

import com.example.lab4.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByYear(int year);

    @Query("SELECT s FROM Student s WHERE s.email LIKE %:domain%")
    List<Student> findByEmailDomain(String domain);

    @Modifying
    @Transactional
    @Query("DELETE FROM Student s WHERE s.code = :code")
    void deleteByCode(String code);
}
