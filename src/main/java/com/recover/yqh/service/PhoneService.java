package com.recover.yqh.service;

import com.recover.yqh.entity.PhoneLogin;

public interface PhoneService {

	int insertPhoneLogin(PhoneLogin phoneLogin);

    PhoneLogin selectPhoneLoginByPrimaryKey(Integer phoneLoginId);
    
    PhoneLogin getPhoneLogin(PhoneLogin phoneLogin);
}
