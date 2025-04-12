package com.cdsg.coursemanagement.repository;

import com.cdsg.coursemanagement.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
}
