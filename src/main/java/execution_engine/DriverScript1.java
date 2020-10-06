package execution_engine;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

import org.testng.annotations.Test;

import config.ActionKeywords;
import utility.ExcelUtils;

public class DriverScript1 {
	public static ActionKeywords actionkeywords;
	public static String sActionkeywords;
	public static Method method[];

	@Test
	public void f() throws IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
	    Properties prop=new Properties();
		FileInputStream data=new FileInputStream("Properties\\data.properties");
		prop.load(data);
		String path = prop.getProperty("path");
		int len = ExcelUtils.lengths(path, 0);
		
		actionkeywords = new ActionKeywords();
		actionkeywords.properties();
		ActionKeywords.createXpath();
		method = actionkeywords.getClass().getDeclaredMethods();
		for (int j = 1; j <= len; j++) {
			sActionkeywords = ExcelUtils.setExcelFile(path, 0, j, 0);
			
			for (int i = 0; i < method.length; i++) {
				if (method[i].getName().equalsIgnoreCase(sActionkeywords)) {
					method[i].invoke(actionkeywords);
					break;
				}
			}
		}

	}
}
