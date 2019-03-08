package com.recover.yqh.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.recover.yqh.common.AppResponse;
import com.recover.yqh.entity.AdverInfo;
import com.recover.yqh.service.AdverService;

@RestController
@RequestMapping("/adver")
public class AdverController {

	@Autowired
	private AdverService adverService;
	
	
    @RequestMapping(value="/getAdverInfo")
    public void getAdverInfo(@RequestParam(value = "adverName", required = false) String adverName) {
    	
    	Map<String,Object> queryMap = new HashMap<>();
    	queryMap.put("adverNameLike", adverName);
    	List<AdverInfo> adverInfoList = adverService.getAdverInfoList(queryMap);
    	System.out.println("adverInfoList >>> " + JSONObject.toJSONString(adverInfoList));
    	
    }

	
}
