package cn.jsong.example.spring.apollo.cfg;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;

import cn.jsong.example.spring.apollo.params.CompanyProperties;
import cn.jsong.example.spring.apollo.params.PersonalProperties;

/**
 * 
 * @author S.J.
 * @date 2018/11/13
 */
@Configuration
@EnableApolloConfig({"application", "sub"})
public class ApolloConfigBeans {
	/*
	 * 采用@ConfigurationProperties注解方式注入配置
	 */
	@Bean
	public PersonalProperties userProperties() {
		return new PersonalProperties();
	}
	
	/*
	 * 采用@ConfigurationProperties注解方式注入配置
	 */
	@Bean
	public CompanyProperties companyProperties() {
		return new CompanyProperties();
	}
	
	// config change listener for namespaces application and sub
	@ApolloConfigChangeListener({ "application", "sub" })
	private void anotherOnChange(ConfigChangeEvent changeEvent) {
		// do something
		for (String key : changeEvent.changedKeys()) {
			ConfigChange change = changeEvent.getChange(key);
			System.out.printf("key=%s, name=%s, oldValue=%s, newValue=%s\n", key, change.getPropertyName(), change.getOldValue(), change.getNewValue());
		}
		
	}
	
}
