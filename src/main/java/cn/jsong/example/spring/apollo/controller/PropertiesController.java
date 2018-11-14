package cn.jsong.example.spring.apollo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;

import cn.jsong.example.spring.apollo.params.CompanyProperties;
import cn.jsong.example.spring.apollo.params.PersonalProperties;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Apollo测试接口集
 * 
 * @author S.J.
 * @date 2018/11/07
 */
@Api(description = "apollo配置测试接口集")
@RestController
@RequestMapping("/v1/properties")
public class PropertiesController {

	/*
	 * 默认application的namespace
	 */
	// java代码注解方式
	@Value("${property.cpu}")
	private String cpu;
	@Value("${property.memory}")
	private String memory;
	
	// @ConfigurationProperties注解方式
	@Autowired
	private PersonalProperties personal;
	
	
	/*
	 * 非application的自定义namespace
	 */
	// java代码注解方式
	@Value("${property.company.addr}")
	private String companyAddr;
	
	// @ConfigurationProperties注解方式
	@Autowired
	private CompanyProperties company;
	
	
	// Apollo API方式
	@ApolloConfig
	private Config config;
	
	
    @ApiOperation(value = "获取所有的配置信息")
    @GetMapping("/all")
    public JSONObject getProperties() {
        
    		// 默认application的namespace
    		JSONObject applicationResult = this.getApplicationProperties();
    		
    		// sub.company的namespace
    		JSONObject nonApplicationResult = this.getNonApplicationProperties();
    		
    		JSONObject result = new JSONObject();
    		result.put("applicationResult", applicationResult);
    		result.put("nonApplicationResult", nonApplicationResult);
    		
        return result;
    }

    private JSONObject getApplicationProperties() {
    		// java代码注解方式
		JSONObject propertyCode = new JSONObject();
		propertyCode.put("cpu", cpu);
		propertyCode.put("memory", memory);
		
		// @ConfigurationProperties注解方式
		JSONObject configurationProperties = JSON.parseObject(JSON.toJSONString(personal));
		
		// Apollo API方式
		JSONObject apolloAPI = getApolloAPIParams();
		
		
		JSONObject applicationResult = new JSONObject();
		applicationResult.put("propertyCode", propertyCode);
		applicationResult.put("configurationProperties", configurationProperties);
		applicationResult.put("apolloAPI", apolloAPI);
    
		return applicationResult;
    }

	private JSONObject getApolloAPIParams() {
		
		Config config = ConfigService.getAppConfig();
		String apiKey = "api.server.os";
		String apiDefaultValue = "defaultServerOS";
		String value = config.getProperty(apiKey, apiDefaultValue);
		
		JSONObject params = new JSONObject();
		params.put("serverOS", value);
		
		return params;
	}
	
	private JSONObject getNonApplicationProperties() {
		// java代码注解方式
		JSONObject propertyCode = new JSONObject();
		propertyCode.put("companyAddr", companyAddr);
		
		// @ConfigurationProperties注解方式
		JSONObject companyProperties = JSON.parseObject(JSON.toJSONString(company));
		
		// Apollo API方式
		JSONObject apolloAPI = getApolloAPIParamsNonApplication();
		
		
		JSONObject nonApplicationResult = new JSONObject();
		nonApplicationResult.put("propertyCode", propertyCode);
		nonApplicationResult.put("configurationProperties", companyProperties);
		nonApplicationResult.put("apolloAPI", apolloAPI);
		
		return nonApplicationResult;
	}
	
	private JSONObject getApolloAPIParamsNonApplication() {
		
		Config config = ConfigService.getConfig("sub");
		String apiKey = "api.department.name";
		String apiDefaultValue = "defaultDepartmentName";
		String value = config.getProperty(apiKey, apiDefaultValue);
		
		JSONObject params = new JSONObject();
		params.put("departmentName", value);
		
		return params;
	}

}