package kui.cams.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@CrossOrigin
@Controller
public class HelloController {

	
	@RequestMapping("/hello")
	@ResponseBody
	public String sayHello() {
		return "hello world2";
	}
	
	@RequestMapping("/weChat")
	@ResponseBody
	public String weChat() {
		return "index";
	}
}
