package com.logistics.webjis.selenium;

public class WebjisTest {
//	private static WebDriver driver;
//
//	private static final String WEB_DRIVER_NAME = "webdriver.chrome.driver";
//	private static final String WEB_DRIVER_PATH = "C:/Users/Administrator/git/us_dw/src/main/resources/chrome/driver/win/chromedriver.exe";
//	private static final int SLEEP_TIME = 500;
//
//	private static final String START_URI = "https://www.webjisulsant.glovis.net/gwjis/gwjis_portal.jsp";
//
//	private static final String LOGIN_BUTTON = "input[type=submit]";
//	private static final String ID_ID = "#K_USER_ID";
//	private static final String PASSWORD_ID = "#K_USER_PASS";
//
//	public static void main(String[] args) throws InterruptedException {
//		
//		setWebDriver();
//		initChromeDriver();
//
//		driver.get(START_URI);
//		try {
//			if (driver.getTitle().contains("Glovis JIS Login")) {
//				sleep(100);
//	
//				WebElement idElement = querySelector(ID_ID);
//				idElement.sendKeys("hdiadmin");
//	
//				sleep(100);
//	
//	
//				WebElement passwordElement = querySelector(PASSWORD_ID);
//				passwordElement.sendKeys("pass1234#");
//				
//				sleep(100);
//				
//				WebElement loginButtonElement = querySelector(LOGIN_BUTTON);
//				loginButtonElement.click();
//				sleep(100);
//				driver.switchTo().alert().accept();
//				sleep(500);
//				driver.get("https://www.webjisulsant.glovis.net/gwjis/jsp/jis/JIS_REPR001.jsp?_FRAME_HEIGHT=763&_MENU_ID=JIS_REPR001&null");
//
//				sleep(1000);
////				List<WebElement> rootMenus = querySelectorAll("td.lmenu");
////				for(WebElement rootMenu : rootMenus) {
////					if("현황관리".equals(rootMenu.getText())) {
////						rootMenu.click();
////					}
////				}
////				sleep(100);
////				List<WebElement> subtMenus = querySelectorAll("td.lmenu_div_cell");
////				for(WebElement subMenu : subtMenus) {
////					if("서열수신현황".equals(subMenu.getText())) {
////						subMenu.click();
////					}
////				}
////				sleep(2000);
////				document.querySelector('input[name=K_JIS_LINE]').value
////				WebElement line = querySelector("input[name='K_JIS_LINE']");
////				line.sendKeys("1");
//
//				/*
//				 * func: IQ
//				MAX_ROWS: 28
//				ORDER_BY: 
//				ORDER_FIELD: 
//				K_JIS_PLANT: 4
//				K_JIS_STATIONCODE: 38
//				K_FROM_DATE: 2023-08-23
//				K_TO_DATE: 2023-08-23
//				K_JIS_LINE: 1
//				K_JIS_ITEM: P32701
//				K_BODY_NUM: 
//				 */
//				JavascriptExecutor js = (JavascriptExecutor) driver;
////				js.executeScript("dijit.byId('K_JIS_PLANT').set('value','4')");
////				js.executeScript("dijit.byId('K_JIS_STATIONCODE').set('value','38')");
////				js.executeScript("dijit.byId('K_JIS_LINE').set('value','1')");
////				js.executeScript("dijit.byId('K_JIS_ITEM').set('value','P32701')");
//
//				js.executeScript("document.querySelector('[name=func]').value='IQ'");
//				sleep(100);
//				js.executeScript("document.querySelector('[name=K_JIS_PLANT]').value=4");
//				sleep(100);
//				js.executeScript("document.querySelector('[name=K_JIS_STATIONCODE]').value=38");
//				sleep(100);
//				js.executeScript("document.querySelector('[name=K_JIS_LINE]').value='1'");
//				sleep(100);
//				js.executeScript("document.querySelector('[name=K_JIS_ITEM]').value='P32701'");
//				sleep(100);
//				js.executeScript("form.submit();");
//				String html = driver.getPageSource();
//				System.out.println(html.substring(0,30000));
////				WebElement searchButton = querySelector("#dijit_form_Button_0_label");
////				searchButton.click();
//				sleep(5000);
//			} else {
//				System.out.println("### something wrong: " + driver.getTitle());
//			}
//		}catch(Exception e) {
//			e.printStackTrace();
//		}finally {
//			//destroyChromeDriver(true);
//		}
//
//		//
//	}
//
//	private static String setWebDriver() {
//		return System.setProperty(WEB_DRIVER_NAME, WEB_DRIVER_PATH);
//	}
//
//	private static void initChromeDriver() {
//		driver = new ChromeDriver();
//	}
//
//	private static void sleep(int time) throws InterruptedException {
//		if(time==0) {
//			time = SLEEP_TIME;
//		}
//		Thread.sleep(time);
//	}
//
//	private static WebElement querySelectorByXpath(String xpath) {
//		return driver.findElement(By.xpath(xpath));
//	}
//	private static WebElement querySelector(String selector) {
//		return driver.findElement(By.cssSelector(selector));
//	}
//	private static List<WebElement> querySelectorAll(String selector) {
//		return driver.findElements(By.cssSelector(selector));
//	}
//
//	private static void destroyChromeDriver(boolean destroy) {
//		if (driver != null && destroy) {
//			driver.quit();
//		}
//	}
}
