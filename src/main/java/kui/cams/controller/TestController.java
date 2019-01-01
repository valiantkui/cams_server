package kui.cams.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin
@Controller
@RequestMapping("/test")
public class TestController {
	
	
	
	@RequestMapping("/test2")
	public String test2() {
		System.out.println("test2()");
		return "redirect:http://www.baidu.com";
	}

	@RequestMapping("/test")
	@ResponseBody
	public void test(String[] subject,String name, String term, MultipartFile file) {
		System.out.println(subject.length);
		for(String s: subject) {
			System.out.println(s);
		}
		BufferedOutputStream bos = null;
		try {
			byte[] bytes = file.getBytes();
		
		
			
			bos = new BufferedOutputStream(new FileOutputStream(File.separator+"home"+File.separator+"kui"+File.separator+"a.jpg"));
			bos.write(bytes);
			bos.flush();
			bos.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	
	}
}
