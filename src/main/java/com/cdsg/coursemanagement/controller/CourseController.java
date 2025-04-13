package com.cdsg.coursemanagement.controller;

import com.cdsg.coursemanagement.dto.CourseDTO;
import com.cdsg.coursemanagement.dto.CourseRequest;
import com.cdsg.coursemanagement.dto.CourseResponse;
import com.cdsg.coursemanagement.service.CourseService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<CourseResponse> getAllCourses() {
        return ResponseEntity.ok(new CourseResponse(courseService.getAllCourses()));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public ResponseEntity<String> updateCourse(@PathVariable Long id, @Valid @RequestBody CourseRequest courseRequest, Authentication authentication, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        String username = authentication.getName();
        CourseDTO courseDTO = courseService.getCourseById(id);

        if(courseDTO != null) {
            if(courseDTO.getInstructorName().equals(username)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Cannot update a course that is not owner.");
            } else {
                courseRequest.setCourseId(id);
                int result = courseService.updateCourse(courseRequest);
                if (result == 1) {
                    return ResponseEntity.ok("Course is updated successfully.");
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body("Course updating is failed.");
                }
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Course is not found.");
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public ResponseEntity<?> deleteCourse(@PathVariable Long id, Authentication authentication) {
        String username = authentication.getName();
        CourseDTO courseDTO = courseService.getCourseById(id);
        if(courseDTO != null) {
            if(courseDTO.getInstructorName().equals(username)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Cannot delete a course that is not owner.");
            } else {
                int result = courseService.deleteCourse(id);
                if (result == 1) {
                    return ResponseEntity.ok("Course is deleted successfully.");
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body("Course deleting is failed.");
                }
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Course is not found.");
        }
    }
}