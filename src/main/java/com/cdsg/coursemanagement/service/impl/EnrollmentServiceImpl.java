package com.cdsg.coursemanagement.service.impl;

import com.cdsg.coursemanagement.dto.EnrollmentDTO;
import com.cdsg.coursemanagement.entity.Course;
import com.cdsg.coursemanagement.entity.Enrollment;
import com.cdsg.coursemanagement.entity.User;
import com.cdsg.coursemanagement.repository.CourseRepository;
import com.cdsg.coursemanagement.repository.EnrollmentRepository;
import com.cdsg.coursemanagement.repository.UserRepository;
import com.cdsg.coursemanagement.service.EnrollmentService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    
    @Override
    public int enrollCourse(EnrollmentDTO enrollmentDTO) {
        try {
            User student = userRepository.findUserByUserName(enrollmentDTO.getStudentName());
            Optional<Course> course = courseRepository.findById(enrollmentDTO.getCourseId());

           if(course.isEmpty()) {
               return 0;
           } else {
               Enrollment existEnrollment = enrollmentRepository.findEnrollmentByStudentAndCourse(student, course.get());
                if(existEnrollment != null) {
                    return 0;
                }
               Enrollment enrollment = new Enrollment();
               enrollment.setStudent(student);
               enrollment.setCourse(course.get());
               enrollment.setEnrolledAt(LocalDateTime.now());
               enrollmentRepository.save(enrollment);
               return 1;
           }
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public List<EnrollmentDTO> getEnrollmentsByStudentId(Long studentId) {
        return enrollmentRepository.findEnrollmentByStudentId(studentId)
                .stream().map(EnrollmentDTO::new).collect(Collectors.toList());
    }
}