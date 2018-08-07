package test.cjh.file;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * 按协议读取文件.
 */
public class FileProtocolRead {

	public void read() throws Exception {
		//
		// String filePath1 =
		// "file:/opt/pay/config/basis/basis/app-config.properties";
		String filePath1 = "/opt/pay/config/basis/basis/app-config.properties";
		//System.out.println(getFileProperties(filePath1));
		System.out.println(getFileProperties2(filePath1));

		String filePath2 = "D:/opt/pay/config/basis/basis/app-config.properties";
		//System.out.println(getFileProperties(filePath2));
		System.out.println(getFileProperties2(filePath2));

		String filePath3 = "classpath:child/app-config.properties";
		//System.out.println(getFileProperties(filePath3));
		System.out.println(getFileProperties2(filePath3));
	}

	/**
	 * Spring中文件读取类
	 * 
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	private Properties getFileProperties(String filePath) throws Exception {
		Properties prop = new Properties();
		File file = ResourceUtils.getFile(filePath);
		FileInputStream s = new FileInputStream(file);
		prop.load(s);
		s.close();

		return prop;
	}

	/**
	 * File直接读取文件
	 * 
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	private Properties getFileProperties2(String filePath) throws Exception {
		Properties prop = new Properties();
		FileInputStream s = new FileInputStream(new File(filePath));
		prop.load(s);
		s.close();

		return prop;
	}
}
