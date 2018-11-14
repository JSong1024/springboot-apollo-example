package cn.jsong.example.spring.apollo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.jsong.example.spring.apollo.dao.DictMapper;
import cn.jsong.example.spring.apollo.po.Dict;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Apollo数据测试接口集
 * 
 * @author S.J.
 * @date 2018/11/07
 */
@Api(description = "数据测试接口集")
@RestController
@RequestMapping("/v1/dict")
public class DictController {
	
	@Autowired
	private DictMapper dictMapper;
	
    @ApiOperation(value = "添加字典信息")
    @PostMapping("/add")
    public String add(@Valid @RequestBody Dict request, BindingResult bindingResult) {
        
    		bindingResult(bindingResult, "add");
    	
    		this.dictMapper.insert(request);
    		
        return "success";
    }
    
    @ApiOperation(value = "查询所有字典信息")
    @GetMapping("/get/all")
    public List<Dict> getAll() {
    	
    		List<Dict> allDicts = this.dictMapper.getAllDicts();
    		
        return allDicts;
    }

    protected void bindingResult(BindingResult bindingResult, String methodName) {
        if (bindingResult.hasErrors()) {
            throw new RuntimeException(bindingResult.getFieldError().getDefaultMessage());
        }
    }
}