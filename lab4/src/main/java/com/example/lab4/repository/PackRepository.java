package com.example.lab4.repository;

import com.example.lab4.entity.Pack;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PackRepository extends JpaRepository<Pack, Long> {
    List<Pack> findByYear(int year);
}
