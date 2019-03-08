package com.recover.yqh.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.recover.yqh.entity.PhoneLogin;
import com.recover.yqh.service.PhoneService;


@RestController
@RequestMapping("/phone")
public class PhoneController {
	
	@Autowired
	private PhoneService phoneService;
	
	@RequestMapping("/add")
	public void addPhone(@RequestParam("phone")String phone){
		
		System.out.println("phone >>> " + phone);
		
		PhoneLogin phoneLogin = new PhoneLogin();
		phoneLogin.setPhone(phone);
		phoneLogin.setMappingUserId(0L);
		phoneLogin.setCreateTime(new Date());
		int id = phoneService.insertPhoneLogin(phoneLogin);
		
		System.out.println("phoneLoginId >>> " + phoneLogin.getPhoneLoginId());
		
	}

}
