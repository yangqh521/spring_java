package com.recover.yqh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recover.yqh.dao.site.PhoneLoginMapper;
import com.recover.yqh.entity.PhoneLogin;
import com.recover.yqh.service.PhoneService;


@Service
public class PhoneServiceImpl implements PhoneService {

	
	@Autowired
	private PhoneLoginMapper phoneLoginMapper;

	@Override
	public int insertPhoneLogin(PhoneLogin phoneLogin) {
		return phoneLoginMapper.insertPhoneLogin(phoneLogin);
	}

	@Override
	public PhoneLogin selectPhoneLoginByPrimaryKey(Integer phoneLoginId) {
		return phoneLoginMapper.selectPhoneLoginByPrimaryKey(phoneLoginId);
	}

	@Override
	public PhoneLogin getPhoneLogin(PhoneLogin phoneLogin) {
		return phoneLoginMapper.getPhoneLogin(phoneLogin);
	}
	
}
