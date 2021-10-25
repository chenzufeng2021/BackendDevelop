package com.example.controller;

import com.example.dto.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

/**
 * @author chenzufeng
 * @date 2021/10/12
 * @usage 接口
 */
@RestController
public class SwaggerController {
    @GetMapping("/helloSwagger")
    public String helloSwagger() {
        return "Hello Swagger !";
    }

    /**
     * 只要这个实体在请求接口的返回值中（即使是泛型），都能映射到实体项
     * @return User
     */
    @PostMapping("/getUser")
    public User getUser(@RequestBody User user) {
        return user;
    }

    @ApiOperation("用户你好")
    @GetMapping("/hello")
    public String hello(@ApiParam("传递的参数为用户名") @RequestParam String userName) {
        return "Hello " + userName;
    }
}
