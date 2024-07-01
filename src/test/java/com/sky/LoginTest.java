package com.sky;

import com.sky.dto.UserLoginDTO;
import com.sky.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LoginTest {
    @Autowired
    private UserService userService;

    @Test
    public void loginSuccess() {
        UserLoginDTO userLoginDTO = new UserLoginDTO();
        userLoginDTO.setUsername("luofan");
        userLoginDTO.setPassword("1234567");
        System.out.println(userService.login(userLoginDTO));
    }

    @Test
    public void loginFail(){
        UserLoginDTO userLoginDTO = new UserLoginDTO();
        userLoginDTO.setUsername("luofan");
        userLoginDTO.setPassword("123456");
        userService.login(userLoginDTO);
    }
}
