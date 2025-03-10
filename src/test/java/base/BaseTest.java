package base;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.microsoft.playwright.*;

import gitMail.GitAutomation;

import java.io.IOException;

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
    public void tearDown() throws IOException, InterruptedException {
    	GitAutomation gitautomation=new GitAutomation();
    	gitautomation.commitAndPushExtentReport();
    	
       if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
    }
}
