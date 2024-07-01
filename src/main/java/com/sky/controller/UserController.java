package com.sky.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sky.constant.JwtClaimsConstant;
import com.sky.constant.MessageConstant;
import com.sky.dto.UserLoginDTO;
import com.sky.dto.UserUpdateDTO;
import com.sky.entity.Student;
import com.sky.entity.Teacher;
import com.sky.entity.User;
import com.sky.exception.AccountNotFoundException;
import com.sky.exception.BaseException;
import com.sky.exception.PasswordEditFailedException;
import com.sky.properties.JwtProperties;
import com.sky.result.Result;
import com.sky.service.StudentService;
import com.sky.service.TeacherService;
import com.sky.service.UserService;
import com.sky.utils.JwtUtil;
import com.sky.vo.UserInfoVO;
import com.sky.vo.UserLoginVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 用户管理
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    // change-test
    @Autowired
    private UserService userService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 登录
     *
     * @param userLoginDTO
     * @return
     */
    @PostMapping("/login")
    public Result<UserLoginVO> login(@RequestBody UserLoginDTO userLoginDTO) {
        log.info("用户登录：{}", userLoginDTO);

        User user = userService.login(userLoginDTO);

        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID, user.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        UserLoginVO userLoginVO = UserLoginVO.builder()
                .id(user.getId())
                .userName(user.getUsername())
                .token(token)
                .memberId(user.getMemberId())
                .type(user.getType())
                .build();

        return Result.success(userLoginVO);
    }

    /**
     * 退出
     *
     * @return
     */
    @PostMapping("/logout")
    public Result<String> logout() {
        return Result.success();
    }

    /**
     * 获取账号列表
     *
     * @return
     */
    @GetMapping("/list")
    public Result<List<UserInfoVO>> getList() {
        List<User> users = userService.list();
        List<UserInfoVO> userInfoVOS = users.stream().map((item) -> {
            UserInfoVO userInfoVO = new UserInfoVO();
            BeanUtils.copyProperties(item, userInfoVO);
            if (item.getMemberId() == 0 && item.getType() == 1) {
                userInfoVO.setName("管理员");
            } else if (item.getMemberId() != 0 && item.getType() == 2) {
                Teacher teacher = teacherService.getById(item.getMemberId());
                userInfoVO.setName(teacher.getName());
            } else {
                Student student = studentService.getById(item.getMemberId());
                userInfoVO.setName(student.getName());
            }
            return userInfoVO;
        }).collect(Collectors.toList());
        return Result.success(userInfoVOS);
    }

    /**
     * 修改账号的用户名和密码
     *
     * @param userUpdateDTO 用户更新DTO类
     * @return
     */
    @Transactional
    @PutMapping("/update")
    public Result update(@RequestBody UserUpdateDTO userUpdateDTO) {
        if (userUpdateDTO.getId() == 0) {
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }
        User user = userService.getById(userUpdateDTO.getId());
        if (userUpdateDTO.getOldPassword() != null) {
            //旧密码对比
            String oldPassword = userUpdateDTO.getOldPassword();
            oldPassword = DigestUtils.md5DigestAsHex(oldPassword.getBytes());
            if (oldPassword.equals(user.getPassword())) {
                String newPassword = userUpdateDTO.getNewPassword();
                if (newPassword.equals(userUpdateDTO.getCheckPassword())) {
                    newPassword = DigestUtils.md5DigestAsHex(newPassword.getBytes());
                    user.setPassword(newPassword);
                } else {
                    throw new PasswordEditFailedException(MessageConstant.PASSWORD_CHECK_FAILED);
                }
            } else {
                throw new PasswordEditFailedException(MessageConstant.PASSWORD_OLD_CHECK_FAILED);
            }
        }
        if (userUpdateDTO.getUserName() != null) {
            user.setUsername(userUpdateDTO.getUserName());
        }
        if (user != null) {
            userService.updateById(user);
        }
        return Result.success();
    }

    /**
     * 修改账号状态
     *
     * @param status 账号状态
     * @return Result类
     */
    @PutMapping("/update/{status}")
    public Result updateStatus(@PathVariable Integer status, @RequestParam Long id) {
        if (id == null || id == 0) {
            throw new BaseException(MessageConstant.UNKNOWN_ERROR);
        }
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getId, id);
        User user = userService.getOne(lqw);
        if (user != null) {
            user.setStatus(status);
            userService.update(user, null);
        } else {
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }
        return Result.success();
    }
}
