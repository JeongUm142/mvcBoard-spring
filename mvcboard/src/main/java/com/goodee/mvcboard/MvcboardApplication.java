package com.goodee.mvcboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan // 컨트롤러, 서비스와 같은 컨포넌트 자식만 가능했음 -> 추가적으로 스캔하여 사용할 수 있도록 
public class MvcboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(MvcboardApplication.class, args);
	}

}
