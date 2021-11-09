package com.example.controller;

import com.example.dto.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

/**
 * @author chenzufeng
 * @date 2021/10/12
 * @usage 接口
 */
@Api(tags = "HelloSwagger接口")
@RestController
public class SwaggerController {
    @ApiOperation(value = "打印Hello Swagger !", notes = "return \"Hello Swagger !\"")
    @GetMapping("/helloSwagger")
    public String helloSwagger() {
        return "Hello Swagger !";
    }

    /**
     * 只要这个实体在请求接口的返回值中（即使是泛型），都能映射到实体项
     * @return User
     */
    @ApiOperation(value = "提交用户信息")
    @PostMapping("/getUser")
    public User getUser(
            @ApiParam(value = "用户实体类User", required = true)
            @RequestBody User user) {
        return user;
    }

    @ApiOperation(value = "Hello {userName}")
    @GetMapping("/hello")
    public String hello(
            @ApiParam(value = "用户名", required = true)
            @RequestParam String userName) {
        return "Hello " + userName;
    }

    /**
     * 直接返回输入参数
     * @param integer
     * @return
     */
    @ApiOperation(value = "直接返回输入参数(Integer)")
    @GetMapping("/returnInteger")
    public Integer returnInteger(
            @ApiParam(value = "Integer", required = true)
            @RequestParam Integer integer) {
        return integer;
    }
}
