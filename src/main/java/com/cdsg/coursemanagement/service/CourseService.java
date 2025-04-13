package com.cdsg.coursemanagement.service;

import com.cdsg.coursemanagement.dto.CourseDTO;
import com.cdsg.coursemanagement.dto.CourseRequest;

import java.util.List;

public interface CourseService {

    int registerCourse(CourseRequest courseRequest);
    List<CourseDTO> getAllCourses();
    CourseDTO getCourseById(Long courseId);
    int updateCourse(CourseRequest courseRequest);
    int deleteCourse(Long courseId);
}
