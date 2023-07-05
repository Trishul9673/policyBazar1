package TestPolicyBazar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.poi.EncryptedDocumentException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Test;
import UtilityPolicyBazar.Utility;
import base.Base;
import pomPolicyBazar.LoginPage;
import pomPolicyBazar.MyAccountPage;
//@Listeners(testPolicyBazar.listener.class)
public class PB_TC_1020_ValidatePolicyBazar extends Base
{
	LoginPage login;
	MyAccountPage myAcc;
	String TCID="PB_TC_1234";

	@BeforeClass
	public void launchPolicyBazaar() throws InterruptedException, IOException
	{
	Utility.wait(1000);
	launchBrowserUsingPropertyFile();
	login= new LoginPage(driver);
	myAcc= new MyAccountPage(driver);
	}
	@BeforeMethod
	public void signInToPolicyBazaar() throws InterruptedException,IOException
	 

	{
	Utility.wait(2000);
	login.clickOnHomePageSignInButton();
	Utility.wait(2000);
	login.enterMobileNum(Utility.readDataFromPropertyFile("mobNum"));
	Utility.wait(2000);
	login.clickOnSignInWithPassword();
	Utility.wait(2000);
	login.enterPassword(Utility.readDataFromPropertyFile("pwd"));
	Utility.wait(2000);
	login.clickOnSignInButton();
	Utility.wait(2000);
	login.clickOnMyAccountButton();
	Utility.wait(2000);
	login.clickOnMyProfileButton();
	Utility.wait(5000);
	Set<String> allPageID = driver.getWindowHandles();

	List<String>l= new ArrayList<>(allPageID);
	String mainPageID = l.get(0);
	String childPageID = l.get(1);
	driver.switchTo().window(childPageID);
	Reporter.log("switching to child page", true);
	Utility.wait(2000);
	}

	@Test
	public void validateUserName() throws EncryptedDocumentException, IOException,
	InterruptedException
	{

		Utility.wait(2000);
	String actualUN = myAcc.getActualUserName();
	String expUN = Utility.readDataFromPropertyFile("UN");
	Assert.assertEquals(actualUN,expUN,"TC is failed actual and expected are notmatching");

	Utility.takeScreenShot(driver, actualUN+" "+TCID);
//Assert.fail();
	}
	@AfterMethod
	public void logoutFromPolicyBazaar()
	{
	myAcc.clickOnLogOutButton();
	}
	@AfterClass

	public void closePoilicyBazaar() throws InterruptedException
	{
	closeBrowser();
}
}
