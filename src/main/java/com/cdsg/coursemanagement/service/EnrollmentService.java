package com.cdsg.coursemanagement.service;

import com.cdsg.coursemanagement.dto.EnrollmentDTO;

import java.util.List;

public interface EnrollmentService {
    int enrollCourse(EnrollmentDTO enrollmentDTO);
    List<EnrollmentDTO> getEnrollmentsByStudentId(Long studentId);
}
