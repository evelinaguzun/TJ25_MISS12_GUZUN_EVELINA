package com.example.lab4.controller;

import com.example.lab4.dto.StudentPreferenceDTO;
import com.example.lab4.entity.*;
import com.example.lab4.exception.ResourceNotFoundException;
import com.example.lab4.repository.*;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/preferences")
public class StudentPreferenceController {

    private final StudentPreferenceRepository prefRepo;
    private final StudentRepository studentRepo;
    private final CourseRepository courseRepo;

    public StudentPreferenceController(StudentPreferenceRepository prefRepo,
                                       StudentRepository studentRepo,
                                       CourseRepository courseRepo) {
        this.prefRepo = prefRepo;
        this.studentRepo = studentRepo;
        this.courseRepo = courseRepo;
    }

    // 🔹 GET → public (oricine autenticat poate vedea)
    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<List<StudentPreference>> getAll(@RequestHeader(value = "If-None-Match", required = false) String ifNoneMatch) {
        List<StudentPreference> prefs = prefRepo.findAll();
        String currentTag = String.valueOf(prefs.hashCode());

        if (ifNoneMatch != null && ifNoneMatch.equals(currentTag)) {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
        }

        return ResponseEntity.ok()
                .eTag(currentTag)
                .body(prefs);
    }

    // 🔒 CREATE → doar ADMIN sau STUDENT
    @PreAuthorize("hasAnyRole('ADMIN','STUDENT')")
    @PostMapping
    public ResponseEntity<StudentPreference> addPreference(@Valid @RequestBody StudentPreferenceDTO dto) {
        Student student = studentRepo.findById(dto.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        Course course = courseRepo.findById(dto.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        StudentPreference pref = new StudentPreference();
        pref.setStudent(student);
        pref.setCourse(course);
        pref.setPreferenceOrder(dto.getPreferenceOrder());

        StudentPreference saved = prefRepo.save(pref);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // 🔒 UPDATE → doar ADMIN
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<StudentPreference> updatePreference(@PathVariable Long id,
                                                              @Valid @RequestBody StudentPreferenceDTO dto) {
        StudentPreference existing = prefRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Preference not found"));

        Student student = studentRepo.findById(dto.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        Course course = courseRepo.findById(dto.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        existing.setStudent(student);
        existing.setCourse(course);
        existing.setPreferenceOrder(dto.getPreferenceOrder());

        StudentPreference updated = prefRepo.save(existing);
        return ResponseEntity.ok(updated);
    }

    // 🔒 DELETE → doar ADMIN
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePreference(@PathVariable Long id) {
        StudentPreference existing = prefRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Preference not found"));

        prefRepo.delete(existing);
        return ResponseEntity.noContent().build();
    }
}
