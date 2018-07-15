package com.xavier.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.xavier.bean.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * User Mappoer
 *
 * @author NewGr8Player
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {


    /**
     * 获得密码
     *
     * @param username 用户名
     */
    @Select("select `password` from t_user where username= #{username}")
    @ResultType(String.class)
    String getPassword(@Param("username") String username);

    /**
     * 获得角色权限
     *
     * @param username 用户名
     * @return user/admin
     */
    @Select("select role from t_user where username= #{username}")
    String getRole(@Param("username") String username);

    /**
     * 修改密码
     */
    @Update("update t_user set password = #{newPassword} where username = #{username}")
    void updatePassword(
            @Param("username") String username,
            @Param("newPassword") String newPassword);

    /**
     * 获得存在的用户
     */
    List<String> getUser();

    /**
     * 封号
     */
    void banUser(String username);

    /**
     * 检查用户状态
     */
    int checkUserBanStatus(String username);

    /**
     * 获得用户角色默认的权限
     */
    @Select("select permission from t_user where username= #{username}")
    String getRolePermission(@Param("username") String username);

    /**
     * 获得用户的权限
     */
    @Select("select permission from t_user where username= #{username}")
    String getPermission(@Param("username") String username);
}
