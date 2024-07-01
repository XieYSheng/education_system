package com.sky.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    private static final long serialVersionUID = 8412349361228608951L;

    private Long id;

    private String name;

    private Integer sex;

    private Integer age;

    private Long majorId;

    private String phone;

}
