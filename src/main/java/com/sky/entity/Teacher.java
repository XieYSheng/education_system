package com.sky.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {
    private static final long serialVersionUID = 8412349361228608952L;

    private Long id;
    private Integer sex;
    private Integer age;
    private String name;
    private String outlook;
    private String degree;
    private String phone;
    private Integer departmentId;

}
