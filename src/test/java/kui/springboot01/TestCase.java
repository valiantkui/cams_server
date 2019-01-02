package kui.springboot01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TestCase {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		/*
		 * String str = DigestUtils.md5DigestAsHex(new
		 * FileInputStream("/home/kui/app/eclipse-inst-linux64.tar.gz"));
		 * System.out.println(str);
		 */
		
		String str = "hello wolrd";
		System.out.println(str.split(" ").length);
		File file= new File("//home//kui//eclipse-workspace//cams//");
		System.out.println(countJavaLine(file));
	}
	
	public static int countJavaLine(File file) throws IOException {
		int count = 0;
		File[] fileList = file.listFiles();
		for(File f: fileList) {
			if(f.isDirectory()) {
				count += countJavaLine(f);
			}else if(f.getName().endsWith(".java")) {
				BufferedReader br = new BufferedReader(new FileReader(f));
				String s = null;
				while((s=br.readLine()) != null) {
					count ++;
				}
				
				br.close();
			}
		}
		
		return count;
	}
}
