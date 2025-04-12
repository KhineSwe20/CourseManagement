package com.cdsg.coursemanagement.repository;

import com.cdsg.coursemanagement.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
