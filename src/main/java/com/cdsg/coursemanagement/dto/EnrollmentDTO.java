package com.cdsg.coursemanagement.dto;

import com.cdsg.coursemanagement.entity.Enrollment;

import com.cdsg.coursemanagement.util.CommonConstant;
import com.cdsg.coursemanagement.util.CommonUtil;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EnrollmentDTO {

    private Long enrollmentId;
    private Long courseId;
    private String courseTitle;
    private Long studentId;
    private String studentName;
    private String enrolledAt;

    public EnrollmentDTO(Enrollment enrollment) {
        this.enrollmentId = enrollment.getId();
        if(enrollment.getCourse() != null) {
            this.courseId = enrollment.getCourse().getId();
            this.courseTitle = enrollment.getCourse().getTitle();
        }
        if(enrollment.getStudent() != null) {
            this.studentId = enrollment.getStudent().getId();
            this.studentName = enrollment.getStudent().getUserName();
        }
        this.enrolledAt = enrollment.getEnrolledAt() != null
                ? CommonUtil.dateToString(CommonConstant.DATE_TIME_FORMAT, enrollment.getEnrolledAt())
                : null;
    }
}
