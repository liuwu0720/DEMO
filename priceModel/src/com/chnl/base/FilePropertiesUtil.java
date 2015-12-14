package com.chnl.base;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
/**
 * 该工具类用于读取配置文件的File.properties 的属性
 */
public class FilePropertiesUtil {
	DbUtil dbUtil = new DbUtil();

	private static final String CONFIG_FILE = "config/File.properties";
	private Map<String, String> configMap = new HashMap<String, String>();
	private static FilePropertiesUtil filePropertiesUtil;
	/**
	 * 单例对象初始化
	 * @return FilePropertiesUtil的对象
	 */
	public static FilePropertiesUtil getInstance() {
		if (filePropertiesUtil == null) {
			filePropertiesUtil = new FilePropertiesUtil();
		}
		return filePropertiesUtil;
	}

	public FilePropertiesUtil() {
		configMap = loadFile(FilePropertiesUtil.CONFIG_FILE);
	}
	/**
	 * 加载配置文件的属性
	 * @param fileName
	 * @return 返回Map
	 */
	protected Map<String, String> loadFile(String fileName) {
		Map<String, String> map = null;
		if ((fileName != null) && (!fileName.trim().equals(""))) {
			InputStream is = FilePropertiesUtil.class.getClassLoader()
					.getResourceAsStream(fileName);
			if (is != null) {
				map = new HashMap<String, String>();
				Properties prop = new Properties();
				try {
					prop.load(is);
					for (Entry<Object, Object> entry : prop.entrySet()) {
						String key = (String) entry.getKey();
						String value = (String) entry.getValue();
						map.put(key, value);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return map;
	}
	/**
	 * 通过属性名获得对应属性值
	 * @param key
	 * @return 属性的值
	 */
	public String getCfg(String key) {
		return configMap.get(key);
	}

}
