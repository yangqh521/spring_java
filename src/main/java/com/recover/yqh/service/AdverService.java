package com.recover.yqh.service;

import java.util.List;
import java.util.Map;

import com.recover.yqh.entity.AdverInfo;

public interface AdverService {

	List<AdverInfo> getAdverInfoList(Map<String,Object> map);
}
