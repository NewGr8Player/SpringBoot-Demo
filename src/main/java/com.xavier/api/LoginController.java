package com.xavier.api;

import com.xavier.common.ResultMap;
import com.xavier.common.util.JWTUtil;
import com.xavier.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/auth")
public class LoginController {

	private final UserMapper userMapper;
	private final ResultMap resultMap;

	@Autowired
	public LoginController(UserMapper userMapper, ResultMap resultMap) {
		this.userMapper = userMapper;
		this.resultMap = resultMap;
	}

	@PostMapping("/login")
	public ResultMap login(@RequestParam("username") String username,
	                       @RequestParam("password") String password) {
		String realPassword = userMapper.getPassword(username);
		if (realPassword == null) {
			return resultMap.fail().code(401).message("用户名错误");
		} else if (!realPassword.equals(password)) {
			return resultMap.fail().code(401).message("密码错误");
		} else {
			return resultMap.success().code(200).message(JWTUtil.createToken(username));
		}
	}

	@GetMapping(path = "/unauthorized/{message}")
	public ResultMap unauthorized(@PathVariable String message) throws UnsupportedEncodingException {
		return resultMap.success().code(401).message(message);
	}
}
