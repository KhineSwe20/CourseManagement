package com.cdsg.coursemanagement.service.impl;

import com.cdsg.coursemanagement.dto.CourseDTO;
import com.cdsg.coursemanagement.dto.CourseRequest;
import com.cdsg.coursemanagement.entity.Course;
import com.cdsg.coursemanagement.entity.User;
import com.cdsg.coursemanagement.repository.CourseRepository;
import com.cdsg.coursemanagement.repository.UserRepository;
import com.cdsg.coursemanagement.service.CourseService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    @Override
    public int registerCourse(CourseRequest courseRequest) {
        try {
            User user = userRepository.findUserByUserName(courseRequest.getInstructorName());
            Course course = Course.builder().title(courseRequest.getTitle())
                    .description(courseRequest.getDescription())
                    .instructor(user)
                    .build();
            courseRepository.save(course);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public List<CourseDTO> getAllCourses() {
        return courseRepository.findAll()
                .stream()
                .map(course -> new CourseDTO(course.getId(), course.getTitle(), course.getDescription(), course.getInstructor().getUserName()))
                .collect(Collectors.toList());
    }

    @Override
    public CourseDTO getCourseById(int id) {
        return null;
    }

    @Override
    public void updateCourse(CourseDTO courseDTO) {

    }

    @Override
    public void deleteCourseById(int id) {

    }
}
