package test;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

import base.BaseTest;
import excelHandling.ExcelUtility;
import reports.ReportManager;

public class TestClass extends BaseTest {
	
	static ExcelUtility excelUtility = new ExcelUtility();
	private ReportManager reportManager;
	
	String username="//input[@placeholder='example@domain.com']";
	String password="//input[@placeholder='············']";
	String signIn="//button[normalize-space(text())='Sign In']";
	
	public Map<String, String> excel() {
		String sheetName = "Credentials";
		return excelUtility.getDataFromExcel(FilePaths.Excel_Path, sheetName);
	}
	
	 @Test(priority = 0)
	 public void browserOpen() {
			boolean isbrowser;
			reportManager = new ReportManager();
			reportManager.createTestSuite("TestSuite_03 Login Page Test",
					"Test cases for the login functionality");
			reportManager.createTestCase("TC_01 : Verify open the browser", "Test case for browser open",
					"No test data required");
	      isbrowser=true;
	      reportManager.logStepWithScreenshot(page, "Browser launched successfully",
					isbrowser ? Status.PASS : Status.FAIL);
	      
	    }
	 @Test(priority = 1)
	 public void invalidLoginCredential() {
		 Map<String, String> userData = excel();
		 String loginUrl = userData.get("loginUrl");
		 String invalidUsername = userData.get("invalidUsername");
		 String invalidPassword = userData.get("invalidPassword");
		 page.navigate(loginUrl);
		 page.waitForSelector("xpath=//h2[normalize-space(text())='Welcome to Earnext']",
					new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));	

		 boolean loginPageIsVisible=page.isVisible("xpath=//h2[normalize-space(text())='Welcome to Earnext']");
		 reportManager.logStepWithScreenshot(page, "Navigated to the login page",
				 loginPageIsVisible ? Status.PASS : Status.FAIL);
		 reportManager.createTestCase("TC_03 : Verify enter Invalid Email and Password", "Test case for invalid login attempt",
					"Username: " + invalidUsername + ", Password: "+invalidPassword);
		 page.fill(username, invalidUsername);
		 page.fill(password, invalidPassword);
		 page.click(signIn);
		 boolean invalidIsVisible=page.isVisible("//span[normalize-space(text())='Invalid email or password.']");
		 reportManager.logStepWithScreenshot(page, "Login failed as expected for invalid credentials",
				 invalidIsVisible ? Status.PASS : Status.FAIL);
	 }
	 @Test(priority = 2)
	 public void validLoginCredential() {
		 Map<String, String> userData = excel();
			String userName = userData.get("username");
			String passWord = userData.get("password");
			
			reportManager.createTestCase("TC_04 : Verify enter Valid Credential", "Test case for valid login attempt",
					"Username: " + username+"Password: "+password);
			
			page.fill(username, userName);
			page.fill(password, passWord);
			page.click(signIn);
			
			page.waitForSelector("xpath=//h2[normalize-space(text())='Client list']",
					new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));
			boolean isVisibleClientList=page.isVisible("//h2[normalize-space(text())='Client list']");
			reportManager.logStepWithScreenshot(page, "Login test result",
					isVisibleClientList ? Status.PASS : Status.FAIL);
	 }
	 @Test(priority = 3)
		public void closeReport() {
			try {
				reportManager.flushReport();
			} catch (Exception e) {
				System.err.println("Error closing report: " + e.getMessage());
			}

		}
}
