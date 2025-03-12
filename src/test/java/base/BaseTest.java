package base;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.microsoft.playwright.*;

import gitMail.EmailUtility;
import gitMail.GitAutomation;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseTest {
    protected Playwright playwright;
    protected Browser browser;
    protected Page page;
    protected static ExtentReports extent;
    protected static ExtentTest test;
  
    @BeforeSuite
    public void setUp() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
    }

    @AfterSuite
    public void tearDown() throws Exception {
    	  if (browser != null) {
              browser.close();
          }
          if (playwright != null) {
              playwright.close();
          }
          
          GitAutomation git=new GitAutomation();
          git.commitAndPushExtentReport();
          	String reportUrl = "https://AdjayaMK2001.github.io/playwright-automation/extent-report.html"; 
	        String toEmail = "demo72986@gmail.com";
	        String subject = "Test Execution Report";

	        EmailUtility emailsend=new EmailUtility();
	       emailsend.sendEmailWithReportLink(toEmail, subject, reportUrl);
    	
     
    }
}
