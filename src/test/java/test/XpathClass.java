package test;import com.aventstack.extentreports.Status;

public class XpathClass {
	public static String signIn="//button[normalize-space(text())='Sign In']";
	public static String register="//a[normalize-space(text())='Register']";
	public static String homePage="xpath=//h2[normalize-space(text())='Customer Login']";
	public static String firstName="(//b[normalize-space(text())='First Name:']/following::input)[1]";
	public static String lastName="(//b[normalize-space(text())='Last Name:']/following::input)[1]";
	public static String address="(//b[normalize-space(text())='Address:']/following::input)[1]";
	public static String city="(//b[normalize-space(text())='City:']/following::input)[1]";
	public static String state="(//b[normalize-space(text())='State:']/following::input)[1]";
	public static String zipCode="(//b[normalize-space(text())='Zip Code:']/following::input)[1]";
	public static String phone="(//b[normalize-space(text())='Phone #:']/following::input)[1]";
	public static String ssn="(//b[normalize-space(text())='SSN:']/following::input)[1]";
	public static String username="(//b[normalize-space(text())='Username:']/following::input)[1]";
	public static String password="(//b[normalize-space(text())='Password:']/following::input)[1]";
	public static String confirm="(//b[normalize-space(text())='Confirm:']/following::input)[1]";
	public static String registerButton="//input[@value='Register']";
	public static String registerSuccess="//p[normalize-space(text())='Your account was created successfully. You are now logged in.']";
	public static String mandatoryError="(//span[@class='error'])[1]";
	public static String loginError="//p[normalize-space(text())='Please enter a username and password.']";
	public static String loginUsername="(//b[normalize-space(text())='Username']/following::input)[1]";
	public static String loginPassword="(//b[normalize-space(text())='Password']/following::input)[1]";
	public static String login="//input[@value='Log In']";
	public static String loginSuccess="//h1[normalize-space(text())='Accounts Overview']";
	public static String logout="//a[normalize-space(text())='Log Out']";
	public static String accountType="//div[@id='openAccountForm']//select[1]";
	public static String openNewAccount="//a[normalize-space(text())='Open New Account']";
	public static String accountOpenSuccess="//h1[normalize-space(text())='Account Opened!']";
	public static String openAccountButton="//input[@value='Open New Account']";
	public static String redirectOpenAccount="//h1[normalize-space(text())='Open New Account']";
	
}
