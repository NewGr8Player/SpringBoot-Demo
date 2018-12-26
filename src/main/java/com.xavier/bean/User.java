package com.xavier.bean;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * User Bean
 *
 * @author NewGr8Player
 */
@Getter
@Setter
@TableName(value = "t_user")
public class User implements Serializable {

	@TableId(type = IdType.UUID)
	private String id;

	@TableField
	private String username;

	@TableField
	private String password;

	@Override
	public String toString() {
		return "User{" +
				"id='" + id + '\'' +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				'}';
	}
}
