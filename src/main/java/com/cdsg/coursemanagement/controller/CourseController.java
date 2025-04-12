package com.cdsg.coursemanagement.controller;

import com.cdsg.coursemanagement.dto.CourseDTO;
import com.cdsg.coursemanagement.dto.CourseRequest;
import com.cdsg.coursemanagement.service.CourseService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public ResponseEntity<?> register(@Valid @RequestBody CourseRequest request, Authentication authentication, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        String instructorName = authentication.getName();
        request.setInstructorName(instructorName);
        int result = courseService.registerCourse(request);
        if(result == 1) {
            return ResponseEntity.ok("Course is registered successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Course registration is failed.");
        }
    }

    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }
}
