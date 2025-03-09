package test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FilePaths {
	static final String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
    public static final String Video_Path = "AutomationReports_Result/Videos";
    public static final  String reportPath = "AutomationReports_Result/Earnext_ExtentReports_" + timestamp + ".html";
    public static final  String Excel_Path="src/test/resources/Excels/LoginDetails.xlsx";
}
