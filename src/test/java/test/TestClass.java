package test;

import java.nio.file.Paths;
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
	
	
	
	public Map<String, String> excel() {
		String sheetName = "Credentials";
		return excelUtility.getDataFromExcel(FilePaths.Excel_Path, sheetName);
	}
	
	 @Test(priority = 0)
	 public void browserOpen() {
	        FileUtils.clearFolder(Paths.get("docs").toAbsolutePath().toString());
			boolean isbrowser;
			reportManager = new ReportManager();
			reportManager.createTestSuite("TestSuite_01 Registration Process Test",
					"Test cases for the register functionality");
			
	      isbrowser=true;
	      
	      
	    }
	 @Test(priority = 1)
	 public void register() {
		 Map<String, String> userData = excel();
		 String loginUrl = userData.get("loginUrl");
		 String firstName=userData.get("FirstName");
		 String lastName=userData.get("LastName");
		 String address=userData.get("Address");
		 String city=userData.get("City");
		 String state=userData.get("State");
		 String zipCode=userData.get("ZipCode");
		 String phone=userData.get("Phone");
		 String ssn=userData.get("SSN");
		 String username=userData.get("Username");
		 String password=userData.get("Password");
		 String confirm=userData.get("Confirm");
		 reportManager.createTestCase("TC_01 : Verify open the url", "Test case for browser open",
					"Login Url: "+loginUrl);
		 page.navigate(loginUrl);
		 page.waitForSelector(XpathClass.homePage,
					new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));	

		 boolean homePageIsVisible=page.isVisible(XpathClass.homePage);
		 reportManager.logStepWithScreenshot(page, "Navigated to the login page",
				 homePageIsVisible ? Status.PASS : Status.FAIL);
		 reportManager.createTestCase("TC_02 : Verify the mandatory fields", "Test case for register the bank","No test data required");
		 page.click(XpathClass.register);
		 page.waitForSelector("xpath=//h1[normalize-space(text())='Signing up is easy!']",
					new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));	
		 page.click(XpathClass.registerButton);
		 boolean mandatoryError=page.isVisible(XpathClass.mandatoryError);
		 reportManager.logStepWithScreenshot(page, "Mandatory Validation",
				 mandatoryError ? Status.PASS : Status.FAIL);
		 
		 reportManager.createTestCase("TC_03 : Verify the registered successfully", "Test case for register the bank","Enter all the excel data");
		 page.click(XpathClass.register);
		 page.fill(XpathClass.firstName, firstName);
		 page.fill(XpathClass.lastName, lastName);
		 page.fill(XpathClass.address, address);
		 page.fill(XpathClass.city, city);
		 page.fill(XpathClass.state, state);
		 page.fill(XpathClass.zipCode, zipCode);
		 page.fill(XpathClass.phone, phone);
		 page.fill(XpathClass.ssn, ssn);
		 page.fill(XpathClass.username, username);
		 page.fill(XpathClass.password, password);
		 page.fill(XpathClass.confirm, confirm);
		 page.click(XpathClass.registerButton);
		 boolean registerSuccess=page.isVisible(XpathClass.registerSuccess);
		 reportManager.logStepWithScreenshot(page, "Login failed as expected for invalid credentials",
				 registerSuccess ? Status.PASS : Status.FAIL);
	 }
	 @Test(priority = 2)
	 public void validLoginCredential() {
		 Map<String, String> userData = excel();
			String loginUsername = userData.get("Username");
			String loginPassword = userData.get("Password");
			reportManager.createTestSuite("TestSuite_02 Login Process Test",
					"Test cases for the login functionality");
			
			 reportManager.createTestCase("TC_01 : Verify the mandatory fields", "Test case for login the bank","No test data required");
			 page.click(XpathClass.logout);
			 page.click(XpathClass.login);
			page.waitForSelector(XpathClass.loginError,
					new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));
			boolean loginError=page.isVisible(XpathClass.loginError);
			reportManager.logStepWithScreenshot(page, "Login test result",
					loginError ? Status.PASS : Status.FAIL);
			reportManager.createTestCase("TC_02 : Verify the correct credentials", "Test case for login the bank","UserName: "+loginUsername+" Password: "+loginPassword);
			page.fill(XpathClass.loginUsername, loginUsername);
			page.fill(XpathClass.loginPassword, loginPassword);
			page.click(XpathClass.login);
			boolean loginsuccess=page.isVisible(XpathClass.loginSuccess);
			reportManager.logStepWithScreenshot(page, "Login test result",
					loginsuccess ? Status.PASS : Status.FAIL);
	 }
	 @Test(priority = 3)
	 public void openNewAccount() {
		 Map<String, String> userData = excel();
			String accountType = userData.get("AccountType");
			
			reportManager.createTestSuite("TestSuite_03 Open New Account",
					"Test cases for the new account functionality");
			 reportManager.createTestCase("TC_01 : Verify the Account Open Session", "Test case for Account open the bank","Account Type: "+accountType);
			page.click(XpathClass.openNewAccount);			
			page.waitForSelector(XpathClass.redirectOpenAccount,
					new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));
			boolean redirectOpenAccount=page.isVisible(XpathClass.redirectOpenAccount);
			reportManager.logStepWithScreenshot(page, "Redirect to open Account",
					redirectOpenAccount ? Status.PASS : Status.FAIL);
			page.selectOption("#type", accountType); 
			page.waitForTimeout(3000);
			page.click(XpathClass.openAccountButton);
			page.waitForSelector(XpathClass.accountOpenSuccess,
					new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));
			boolean openAccountSuccess=page.isVisible(XpathClass.accountOpenSuccess);
			reportManager.logStepWithScreenshot(page, "Account Open test result",
					openAccountSuccess ? Status.PASS : Status.FAIL);
	 }

	 @Test(priority = 4)
		public void closeReport() {
			try {
				reportManager.flushReport();
			} catch (Exception e) {
				System.err.println("Error closing report: " + e.getMessage());
			}

		}
}
