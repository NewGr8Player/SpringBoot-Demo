package com.xavier;

import com.xavier.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ApplicationTest {

	@Autowired
	private UserService userService;

	@Test
	public void test(){
		System.out.println(userService.searchById("1"));
	}
}
