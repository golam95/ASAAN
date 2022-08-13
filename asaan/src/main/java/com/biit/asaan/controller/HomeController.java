package com.biit.asaan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import com.biit.asaan.model.ContactInfo;
import com.biit.asaan.model.ProdutCategory;
import com.biit.asaan.model.User;

@Controller
public class HomeController {

	@GetMapping("/")
	public String homeFrom() {
		return "home";
	}

	@GetMapping("/login")
	public String loginFrom() {
		return "login";
	}

	@GetMapping("/customerDashboard")
	public String customerDashboardFrom() {
		return "customerDashboard";
	}

	@GetMapping("/about")
	public String aboutFrom() {
		return "about";
	}

	@GetMapping("/service")
	public String serviceFrom() {
		return "service";
	}

	@GetMapping("/access-denied")
	public String showAccessDenied() {
		return "accessdenied";
	}

	@GetMapping("/demoAjax")
	public String demoAjax() {
		return "demoAjax";
	}

	@PostMapping(value = "/globalsaveDemo")
	@ResponseBody
	public String aaddemoAjax(@RequestBody User user) {
		return user.getUserName();
	}

}
