package TestPolicyBazar;

import java.io.IOException;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import UtilityPolicyBazar.Utility;
import base.*;

public class listener extends Base implements ITestListener
{

public void onTestFailure(ITestResult result) 
{
	try {
		Utility.takeScreenShot(driver, result.getName());
	} catch (IOException e)
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	Reporter.log("test case failed",true);
}

	public void onTestSuccess(ITestResult result)
{
	Reporter.log(" TC"+result.getName()+" Passed successfully",true);	
	}

	public void onTestStart(ITestResult result) 
{
	Reporter.log("TC execution started for TC "+result.getName(),true);
	
		
	}
}
