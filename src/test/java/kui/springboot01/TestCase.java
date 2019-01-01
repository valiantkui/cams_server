package kui.springboot01;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.util.DigestUtils;

public class TestCase {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		/*
		 * String str = DigestUtils.md5DigestAsHex(new
		 * FileInputStream("/home/kui/app/eclipse-inst-linux64.tar.gz"));
		 * System.out.println(str);
		 */
		
		String str = "hello wolrd";
		System.out.println(str.split(" ").length);
		
	}
}
