package com.xavier.api;

import com.xavier.common.ResultMap;
import com.xavier.common.jwt.JWTGen;
import com.xavier.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ResultMap resultMap;
    @Autowired
    private JWTGen jwtGen;

    @PostMapping("/login")
    public ResultMap login(@RequestParam("username") String username,
                           @RequestParam("password") String password) {
        String realPassword = userMapper.getPassword(username);
        if (realPassword == null) {
            return resultMap.fail().code(401).message("用户名错误");
        } else if (!realPassword.equals(password)) {
            return resultMap.fail().code(401).message("密码错误");
        } else {
            return resultMap.success().code(200).message(jwtGen.createToken(username));
        }
    }

    @GetMapping(path = "/unauthorized/{message}")
    public ResultMap unauthorized(@PathVariable String message) {
        return resultMap.success().code(401).message(message);
    }
}
