package reports;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.microsoft.playwright.Page;
import com.aventstack.extentreports.Status;

import test.FilePaths;

public class ReportManager {
	 private static ExtentReports extent;
	    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
	    private static ThreadLocal<ExtentTest> suite = new ThreadLocal<>();

	    public static ExtentReports getReportInstance() {
	        if (extent == null) {
	            // Generate timestamp for the report filename
	                        
	            // Initialize the Spark Reporter
	            ExtentSparkReporter htmlReporter = new ExtentSparkReporter(FilePaths.reportPath);
	            htmlReporter.config().setTheme(Theme.STANDARD); // Use LIGHT theme
	            htmlReporter.config().setDocumentTitle("Test Suite Report");
//	            htmlReporter.config().setReportName("Execution Summary with Test Suites");
	            htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
	            
	            // Configure CSS for better UI
	            htmlReporter.config().setCss(".report-name { color: #2E86C1; font-size: 20px; }");

	            extent = new ExtentReports();
	            extent.attachReporter(htmlReporter);
	            
	            // Adding system information to the report
	            extent.setSystemInfo("Environment", "QA");
	            extent.setSystemInfo("Host Name", System.getenv("COMPUTERNAME"));
	            extent.setSystemInfo("OS", System.getProperty("os.name"));
	            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
	            extent.setSystemInfo("User", System.getProperty("user.name"));
	            extent.setSystemInfo("Browser", "Chrome 110.0");
	        }
	        return extent;
	    }

	    // Create or get the test suite
	    public static ExtentTest createTestSuite(String suiteName, String description) {
	        ExtentTest extentSuite = getReportInstance().createTest(suiteName, description);
	        suite.set(extentSuite);
	        return extentSuite;
	    }

	    // Create a test case
	    public static ExtentTest createTestCase(String testCaseName, String description,String testData) {
	        if (suite.get() != null) {
	            ExtentTest extentTest = suite.get().createNode(testCaseName, description);
	            extentTest.info("Test Data: " + testData);

	            test.set(extentTest);
	            return extentTest;
	        } else {
	            return createTest(testCaseName, description,testData);
	        }
	    }

	    // Create a test
	    public static ExtentTest createTest(String testName, String description,String testData) {
	        ExtentTest extentTest = getReportInstance().createTest(testName, description);
	        extentTest.info("Test Data: " + testData);
	        test.set(extentTest);
	        return extentTest;
	    }

	    // Log steps with description
	    public static void logStep(String stepDescription, Status status) {
	        String formattedDescription = "[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] " + stepDescription;
	        test.get().log(status, formattedDescription);
	    }

	    // Log steps with screenshots
	    public static void logStepWithScreenshot(Page page, String stepDescription,Status status) {
	        try {
	        	String screenshotPath = Paths.get("AutomationReports_Result/Screenshots/Step_" 
	                    + new SimpleDateFormat("yyyyMMdd_HHmmssSSS").format(new Date()) + ".png").toAbsolutePath().toString();

	                      page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(screenshotPath)));
	                      String base64Image = encodeImageToBase64(screenshotPath);
	            // Log the step with the screenshot
//	            test.get().log(status, stepDescription, MediaEntityBuilder.createScreenCaptureFromBase64String(base64Image).build());
	            String imgTag = "<img src='data:image/png;base64," + base64Image + "' width='500px' height='300px'>";
	            test.get().log(status, stepDescription + "<br>" + imgTag);
	        } catch (Exception e) {
	            test.get().log(Status.WARNING, "Screenshot could not be attached: " + e.getMessage());
	        }
	    }

	    public void attachVideo(String videoPath) {
	        if (videoPath != null) {
	            // Log the video link in the report
	            test.get().log(Status.INFO, "Test Video: <a href='" + videoPath + "' target='_blank'>View Recording</a>");
	        } else {
	            test.get().log(Status.WARNING, "Video could not be attached.");
	        }
	    }
	    public static ExtentTest getTest() {
	        return test.get();
	    }
	    // Flush the report
	    public static void flushReport() {
	        if (extent != null) {
	            extent.flush();
	        }
	    }
	    private static String encodeImageToBase64(String imagePath) {
	        try {
	            File file = new File(imagePath);
	            FileInputStream fileInputStream = new FileInputStream(file);
	            byte[] imageBytes = new byte[(int) file.length()];
	            fileInputStream.read(imageBytes);
	            fileInputStream.close();
	            return Base64.getEncoder().encodeToString(imageBytes);
	        } catch (Exception e) {
	            return null;
	        	}
	    	}
}
