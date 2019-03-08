package com.recover.yqh.dao.dsp;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.recover.yqh.entity.AdverInfo;

@Repository
public interface AdverInfoMapper {
    
	List<AdverInfo> getAdverInfoList(Map<String,Object> map);
	
	
}