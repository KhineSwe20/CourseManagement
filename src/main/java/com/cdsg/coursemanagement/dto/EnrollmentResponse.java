package com.cdsg.coursemanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class EnrollmentResponse {

    private List<EnrollmentDTO> enrollments;
}
