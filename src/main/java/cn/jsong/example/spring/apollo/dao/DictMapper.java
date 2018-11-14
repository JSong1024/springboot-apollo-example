package cn.jsong.example.spring.apollo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import cn.jsong.example.spring.apollo.po.Dict;

/**
 * 
 * @author S.J.
 * @date 2018/11/13
 */
@Mapper
public interface DictMapper {

	@Insert("insert into dict(name,code) values(#{name},#{code})")
    public void insert(Dict dict);
    
    @Select("select * from dict")
    public List<Dict> getAllDicts();
}
