package testscripts.webportal;

import java.awt.Window;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.poi.ss.formula.functions.Function;
import org.apache.tools.ant.taskdefs.email.Message;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import browsersetup.BaseClass;
import pageobjects.webportal.AmazonCartPage;
import pageobjects.webportal.AmazonDeliveryAddressPage;
import pageobjects.webportal.AmazonDeliveryOptionsPage;
import pageobjects.webportal.AmazonHomePage;
import pageobjects.webportal.AmazonLoginEmailPage;
import pageobjects.webportal.AmazonLoginPasswordPage;
import pageobjects.webportal.AmazonProductDetailPage;
import pageobjects.webportal.AmazonSearchResultPage;
import pageobjects.webportal.FrameStylerPage;
import pageobjects.webportal.LenskartPage;
import reporting.ListenerClass;
import testdata.generictestdata.ConfigurationData;
import utilities.ExcelUtilities;
import utilities.JavaScriptExecutor;

@Listeners(ListenerClass.class)
public class FrameStylerScripts extends BaseClass 
{
	@Test(retryAnalyzer = helpers.RetryMechanism.class, groups = { "Regression", "Web Portal", "Specsavers" })
	@Parameters({ "environment", "automationType" })
	public void addToFavorite(String environment, String automationType) throws InterruptedException {
		// JavaScriptExecutor.clickElementUsingJavaScript(getDriver(),
		// rejectOffer);
		// JavaScriptExecutor.clickElementUsingJavaScript(getDriver(), );
		//		Alert alert = getDriver().switchTo().alert();
		//		alert.accept();
		 /*JFrame parent = new JFrame();
		 JOptionPane.showMessageDialog(parent, "Java is fun!");*/
		JFrame frame = new JFrame("Swift Framework");		
			JOptionPane.showMessageDialog(frame, 
					"Are  you done with scanning image ");
			
	/*	Scanner scanner = new Scanner(System.in);
		System.out.print("Are done with Scanning image: ");
		String result = scanner.nextLine();*/
		/*
		 * System.out.print("Enter your age: "); int age = scanner.nextInt();
		 */

		/*if (result.equalsIgnoreCase("Y")) {*/
			FrameStylerPage FrameStylerPage = new FrameStylerPage(getDriver());
			// FrameStylerPage.clickRecordVideo();
			// Thread.sleep(100000);

			FrameStylerPage.clickFrame1();
			Thread.sleep(6000);
			FrameStylerPage.clickCloseButton();
			Thread.sleep(3000);
			FrameStylerPage.clickFrame2();
			Thread.sleep(6000);
			// FrameStylerPage.clickViewDetails();
			FrameStylerPage.clickFavourate1();
			Thread.sleep(3000);
			FrameStylerPage.clickCloseButton();
			Thread.sleep(3000);
			FrameStylerPage.clickFavourate2();
			Thread.sleep(3000);
			/*
			 * Set handles = getDriver().getWindowHandles();
			 * System.out.println(handles);
			 * 
			 * for (String handle1 : getDriver().getWindowHandles()) {
			 * System.out.println(handle1);
			 * getDriver().switchTo().window(handle1); }
			 * 
			 * FrameStylerPage.clickBookAnAppointment();
			 */
	//	}
	}

}
