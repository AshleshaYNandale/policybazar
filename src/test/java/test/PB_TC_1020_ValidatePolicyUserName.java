package test;

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

import Base.base;
import UtilityPolicyBazar.Utility;
import pomPolicyBazar.LoginPage;
import pomPolicyBazar.MyAccountPage;

public class PB_TC_1020_ValidatePolicyUserName extends base {
	
	LoginPage login;
	MyAccountPage myAcc;
    String TCID="PB_TC_1234";
 @BeforeClass
	public void launchPolicyBazar()throws InterruptedException,IOException
	{
	  Utility.wait(100);
	  launchBrowserUsingPropertyFile();
		login= new LoginPage(driver);
		myAcc=new MyAccountPage(driver);  
		}
@BeforeMethod
	public void signInToPolicyBazar()throws InterruptedException,InterruptedException,IOException
	{
	Utility.wait(1000);
		login.clickOnHomePageSignInButton();
		Utility.wait(1000);
		
		login.enterMobileNum(Utility.readDataFromPropertyFile("mobNum"));
		Utility.wait(1000);
		
		login.clickOnSignInWithPassword();
		Utility.wait(1000);;
		
		login.enterPassword(Utility.readDataFromPropertyFile("pwd"));
		Utility.wait(2000);

		login.clickOnSignInButton();
  	    Utility.wait(2000);

		login.clickOnMyAccountButton();
		Utility.wait(2000);

		login.clickOnMyProfileButton();
        Utility.wait(3000);

		Set<String>allPageID=driver.getWindowHandles();
		List<String>l=new ArrayList<>(allPageID);
		String childPageID=l.get(1);
		
		driver.switchTo().window(childPageID);
		Reporter.log("switching to child page",true);
		Utility.wait(2000);
}
@Test
	public void validateUserName()throws EncryptedDocumentException,InterruptedException,IOException
	{
		Utility.wait(1000);
		String actualUN=myAcc.getActualUserName();
		String expUN=Utility.readDataFromPropertyFile("UN");
		Assert.assertEquals(actualUN,expUN,"TC is failed actual and expected are not matching");
		Utility.takeScreenshot(driver,actualUN+""+TCID);
	}	
@AfterMethod
	public void logOutFromPolicyBazar()
	{
		myAcc.clickOnLogOutButton();
	}
@AfterClass
	 public void closePolicyBazar()throws InterruptedException
	 {
	closeBrowser();
}
}

