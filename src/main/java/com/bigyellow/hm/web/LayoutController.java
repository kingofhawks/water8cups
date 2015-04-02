package com.bigyellow.hm.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LayoutController {
	@RequestMapping("/layout")
	public String getLayoutPage() {
		return "layout";
	}
}
