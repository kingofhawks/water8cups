
package com.bigyellow.hm.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author huang.zhengyu@wuxi.stee.stengg.com.cn
 * @version 1.0
 * @date 2013-10-23
 */

@Controller
public class IndexController {

	@RequestMapping("/index")
	public String getIndexPage() {
		// This will resolve to /WEB-INF/html/index.html
		return "index";
	}

}
