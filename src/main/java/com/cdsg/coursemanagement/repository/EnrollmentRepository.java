package com.cdsg.coursemanagement.repository;

import com.cdsg.coursemanagement.entity.Course;
import com.cdsg.coursemanagement.entity.Enrollment;
import com.cdsg.coursemanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    Enrollment findEnrollmentByStudentAndCourse(User student, Course course);
    List<Enrollment> findEnrollmentByStudentId(Long studentId);
}
