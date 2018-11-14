package cn.jsong.example.spring.apollo.params;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 
 * @author S.J.
 * @date 2018/11/07
 */
@ConfigurationProperties("personal")
public class PersonalProperties {

	private String name;
	private Integer age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
}
