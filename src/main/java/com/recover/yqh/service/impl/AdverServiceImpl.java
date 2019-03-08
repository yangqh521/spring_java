package com.recover.yqh.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recover.yqh.dao.dsp.AdverInfoMapper;
import com.recover.yqh.entity.AdverInfo;
import com.recover.yqh.service.AdverService;

@Service
public class AdverServiceImpl implements AdverService {
	
	@Autowired
	private AdverInfoMapper adverInfoMapper;

	@Override
	public List<AdverInfo> getAdverInfoList(Map<String, Object> map) {
		return adverInfoMapper.getAdverInfoList(map);
	}

}
