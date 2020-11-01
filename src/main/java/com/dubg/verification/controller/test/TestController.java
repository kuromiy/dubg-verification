package com.dubg.verification.controller.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	@GetMapping("/test")
	public String index() {
		System.out.println("test!");
		return "Hello, World";
	}
}
