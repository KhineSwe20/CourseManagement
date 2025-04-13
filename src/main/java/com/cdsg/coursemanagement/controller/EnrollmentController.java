package com.cdsg.coursemanagement.controller;

import com.cdsg.coursemanagement.dto.EnrollmentDTO;
import com.cdsg.coursemanagement.dto.EnrollmentResponse;
import com.cdsg.coursemanagement.service.EnrollmentService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @PostMapping("/courses/{courseId}/enroll")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<String> enrollCourse(@PathVariable Long courseId, Authentication authentication) {
        String username = authentication.getName();

        EnrollmentDTO enrollmentDTO = new EnrollmentDTO();
        enrollmentDTO.setCourseId(courseId);
        enrollmentDTO.setStudentName(username);

        int result = enrollmentService.enrollCourse(enrollmentDTO);
        if(result == 1) {
            return ResponseEntity.ok("Course is enrolled successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Course enrollment is failed.");
        }
    }

    @GetMapping("/students/{studentId}/courses")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<EnrollmentResponse> getEnrolledCourses(@PathVariable Long studentId) {
        return ResponseEntity.ok(new EnrollmentResponse(enrollmentService.getEnrollmentsByStudentId(studentId)));
    }
}