package com.cdsg.coursemanagement.entity;

import com.cdsg.coursemanagement.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "username")
    private String userName;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToMany(mappedBy = "instructor")
    private Set<Course> courses;

    @OneToMany(mappedBy = "student")
    private Set<Enrollment> enrollments;

}
