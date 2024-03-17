package com.spring.test.junit;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringTestApplication.class, args);
	}

	/*@Bean
	ApplicationRunner runner(RestTemplateBuilder restTemplateBuilder) {
		return args -> {
			var restTemplate = restTemplateBuilder.build();
			var response = restTemplate.getForEntity("http://ip.jsontest.com/", IpModel.class);
			System.out.println(response.getBody().ip);
		};
	}

	static class IpModel {
		String ip;

		IpModel() {}
		IpModel(String ip) { this.ip = ip; }
		String getIp() { return ip; }
		void setIp(String ip) { this.ip = ip; }
	}*/
}
