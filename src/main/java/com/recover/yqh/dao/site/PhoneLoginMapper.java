package com.recover.yqh.dao.site;

import org.springframework.stereotype.Repository;

import com.recover.yqh.entity.PhoneLogin;

@Repository
public interface PhoneLoginMapper {

    int insertPhoneLogin(PhoneLogin phoneLogin);

    PhoneLogin selectPhoneLoginByPrimaryKey(Integer phoneLoginId);
    
    PhoneLogin getPhoneLogin(PhoneLogin phoneLogin);
    
}