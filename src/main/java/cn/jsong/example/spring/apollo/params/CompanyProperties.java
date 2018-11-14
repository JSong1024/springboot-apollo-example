package cn.jsong.example.spring.apollo.params;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * @author S.J.
 * @date 2018/11/07
 */
@Configuration
@ConfigurationProperties("company")
public class CompanyProperties {

	private String name;
	private String createTime;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	@Override
	public String toString() {
		return "CompanyProperties [name=" + name + ", createTime=" + createTime + "]";
	}
	
}
