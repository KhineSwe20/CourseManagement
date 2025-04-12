package com.cdsg.coursemanagement.dto;

import com.cdsg.coursemanagement.entity.Course;
import com.cdsg.coursemanagement.entity.Enrollment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO {
    private Long courseId;
    private String title;
    private String description;
    private String instructorName;
}
