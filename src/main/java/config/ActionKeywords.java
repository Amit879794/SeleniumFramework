package config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import custom.report.Report;
import extent_report.ReportExtent;
import utility.ExcelUtils;

public class ActionKeywords {
	public static WebDriver driver;
	static String path = null;
	static String locate = null;
	static Map<String, String> map = new HashMap<String, String>();
	static int r = 1;
	static Logger log = Logger.getLogger(ActionKeywords.class);
	static Properties prop=new Properties();
	public void properties() throws IOException
	{
		FileInputStream data=new FileInputStream("Properties\\data.properties");
		prop.load(data);
		path=prop.getProperty("path");
		locate=prop.getProperty("locate");
	}

	public static void createXpath() throws IOException {
		int len = ExcelUtils.lengths(locate, 0);
		for (int i = 1; i <= len; i++) {

			String k = ExcelUtils.setExcelFile(locate, 0, i, 0);
			String v = ExcelUtils.setExcelFile(locate, 0, i, 1);
			map.put(k, v);
			Report.reportBegin();
		}

	}

	public static String getXpaths(String s) throws IOException {
		Iterator<Map.Entry<String, String>> itr = map.entrySet().iterator();
		while (itr.hasNext()) {
			Map.Entry<String, String> entry = itr.next();

			if (s.equalsIgnoreCase(entry.getKey()))
				return entry.getValue();
		}
		return null;
	}

	public static void initialization() throws IOException {
		PropertyConfigurator.configure("log4j.properties");
		Logger.getRootLogger().setLevel(Level.INFO);
		String path1 = ExcelUtils.setExcelFile(path, 0, r, 2);
		System.setProperty("webdriver.chrome.driver", path1);
		r++;
		driver = new ChromeDriver();
		log.info("WebDriver initialization Successful");
		Report.reportMain("intialization", "PASS", "intiatization of webdriver is successfull");
	}

	public static void navigate() throws Exception {

		log.setLevel(Level.INFO);
		try {
			PropertyConfigurator.configure("log4j.properties");

			String text = ExcelUtils.setExcelFile(path, 0, r, 2);

			r++;
			driver.get(text);
			log.info("URL successfully opened");
			Report.reportMain("URL", "PASS", "Url is opening successfully");
			ReportExtent.generateReport(driver, "navigate", "url", "PASS");
		} catch (Exception e) {
			log.fatal("refused to connect to url");
			ReportExtent.generateReport(driver, "navigate", "url", "FAIL");
			Report.reportMain("URL", "FAILED", "Url not responding");
		}
	}

	public static void sendKeys() throws Exception {
		String name = ExcelUtils.setExcelFile(path, 0, r, 1);
		String path1 = ActionKeywords.getXpaths(name);
		String text = ExcelUtils.setExcelFile(path, 0, r, 2);
		Logger.getRootLogger().setLevel(Level.INFO);
		try {
			PropertyConfigurator.configure("log4j.properties");

			r++;
			driver.findElement(By.xpath(path1)).clear();
			driver.findElement(By.xpath(path1)).sendKeys(text);
			log.info(name + " successfully passed");
			ReportExtent.generateReport(driver, name, name, "PASS");
			Report.reportMain(name, "PASS", "Text box is working");
		} catch (Exception e) {
			log.fatal("unable to find sendkeys element");
			ReportExtent.generateReport(driver, name, name, "FAIL");
			Report.reportMain(name, "FAIL", "Text box is working");

		}

	}

	public static void submit() throws Exception {
		String name = ExcelUtils.setExcelFile(path, 0, r, 2);
		String path1 = ActionKeywords.getXpaths(name);
		Logger.getRootLogger().setLevel(Level.INFO);
		try {
			PropertyConfigurator.configure("log4j.properties");

			r++;
			driver.findElement(By.xpath(path1)).submit();
			log.info(name + " Successfully submitted");
			Report.reportMain(name, "PASS", "Submination is being done");
			ReportExtent.generateReport(driver, name, name, "PASS");
		} catch (Exception e) {
			log.fatal("Unable to locate submit button");
			ReportExtent.generateReport(driver, name, name, "FAIL");
			Report.reportMain(name, "FAIL", "Submination is not being done");
		}
	}

	public static void click() throws Exception {
		String name = ExcelUtils.setExcelFile(path, 0, r, 1);
		String path1 = ActionKeywords.getXpaths(name);
		Logger.getRootLogger().setLevel(Level.INFO);
		try {
			PropertyConfigurator.configure("log4j.properties");

			r++;

			driver.findElement(By.xpath(path1)).click();
			log.info(name + " successfully passed");
			Report.reportMain(name, "PASS", "Button is being pressed successfully");
			ReportExtent.generateReport(driver, name, name, "PASS");

		} catch (Exception e) {
			log.fatal("Unable to click the button");
			ReportExtent.generateReport(driver, name, name, "FAIL");
			Report.reportMain(name, "FAIL", "Button is not being pressed");

		}
	}

	public static void select() throws Exception {
		String name = ExcelUtils.setExcelFile(path, 0, r, 1);
		Logger.getRootLogger().setLevel(Level.INFO);
		try {
			PropertyConfigurator.configure("log4j.properties");

			String path1 = ActionKeywords.getXpaths(name);
			String text = ExcelUtils.setExcelFile(path, 0, r, 2);
			r++;
			Select air = new Select(driver.findElement(By.xpath(path1)));
			air.selectByVisibleText(text);
			log.info(name + " successfull passed");
			Report.reportMain(name, "PASS", "Selection of the element is succcessfull");
			ReportExtent.generateReport(driver, name, name, "PASS");
		} catch (Exception e) {
			log.fatal("Select element is not visible");
			ReportExtent.generateReport(driver, name, name, "FAIL");
			Report.reportMain(name, "FAIL", "Selection of the element is unsucccessfull");
		}
	}

	public static void closeBrowser() throws Exception {
		Logger.getRootLogger().setLevel(Level.INFO);
		try {
			PropertyConfigurator.configure("log4j.properties");

			r++;
			driver.quit();
			log.info("Browser successfully closed");
			Report.reportMain("close browser", "PASS", "Browser successfully closed");
			Report.reportEnd();
			ReportExtent.generateReport(driver, "closeBrowser", "closeBrowser", "PASS");
		} catch (Exception e) {
			log.fatal("Unable to close browser");
			ReportExtent.generateReport(driver, "closeBrowser", "closeBrowser", "FAIL");
			Report.reportMain("close browser", "FAIL", "Browser NOT responding");
		}

	}

	public static void implicitWait() {
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}
}
