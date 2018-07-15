package com.xavier.api;

import com.xavier.bean.User;
import com.xavier.service.UserService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User Controller
 *
 * @author NewGr8Player
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequiresRoles(logical = Logical.OR, value = {"user", "admin"})
    @GetMapping("/id/{id}")
    public User findById(@PathVariable("id") String id) {
        return this.userService.searchById(id);
    }
}
