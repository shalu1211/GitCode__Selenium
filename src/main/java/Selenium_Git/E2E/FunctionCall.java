package Selenium_Git.E2E;

import java.util.Properties;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FunctionCall {

	/* ******** Generate Random Number for Repo and Issue ID ******* */
	Random rand = new Random();
	int randomNum = rand.nextInt((2000 - 100) + 1) + 100;
	public String repoName = "Test" + randomNum;
	public String issue_name = "Test_Issue1_" + randomNum;



	

	public void login(WebDriver driver, Properties prop) {


		WebElement email = driver.findElement(By.id("login_field"));
		WebElement passwd = driver.findElement(By.id("password"));
		WebElement sign_in = driver.findElement(By.name("commit"));


		/* ******** Enter loginId******* */
		email.sendKeys(prop.getProperty("Username"));

		/* ******** Enter Password******* */
		passwd.sendKeys(prop.getProperty("Password"));

		/* ******** Enter click button******* */
		sign_in.click();

		System.out.println("Login Successfully");

	}

	public void createRepo(WebDriver driver) throws InterruptedException {
		/* ********Click on plus button******* */
		driver.findElement(By.xpath("//*[@class='octicon octicon-plus']")).click();

		/* ********Click on New repository******* */
		driver.findElement(By.xpath("//*[@class='dropdown-item']")).click();

		/* ********Enter repository name******* */
		driver.findElement(By.id("repository_name")).sendKeys(repoName);

		/* ********Enter Description******* */
		driver.findElement(By.id("repository_description")).sendKeys("Test_description");

		/* ********Scroll down******* */
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,150)");


		Thread.sleep(2000);

		/* ********Click on Create Repository button******* */
		driver.findElement(By.xpath("//button[@class='btn btn-primary first-in-line']")).click();
		System.out.println("Repository created successfully");

	}

	public void createIssue(WebDriver driver) {

		/*
		 * Random rand = new Random(); int randomNum = rand.nextInt((20 - 10) + 1) + 10;
		 * String issue_name = "test_issue" + randomNum;
		 */

		/* ********Click on issues link****** */
		driver.findElement(By.xpath("//span[contains(text(),'Issues')]")).click();

		/* ******** Click on New Issue Button ******* */
		driver.findElement(By.xpath("//span[@class='d-none d-md-block']")).click();

		/* ******** Enter Issue Title ******* */
		driver.findElement(By.xpath("//input[@id='issue_title']")).sendKeys(issue_name);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,150)");

		/* ******** submit new issue  button ******* */
		driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();

		System.out.println("first issue created successfully");

	}

	public void createIssue2(WebDriver driver) throws InterruptedException {

		/* ******** Capture Id of issue 1 ******* */
		String previousIssue_id = driver.findElement(By.xpath("//span[@class='gh-header-number']")).getText();

		/* ******** Click on new issues******* */
		driver.findElement(By.xpath("//a[@class='btn btn-sm btn-primary m-0 ml-2 ml-md-2']")).click();

		/* ******** CEnter title ******* */
		driver.findElement(By.xpath("//input[@id='issue_title']")).sendKeys("New issue Raised - Prev Issue ID is : " + previousIssue_id);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,150)");

		/* ******** submit new issue  button ******* */
		driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();

		System.out.println("second issue created successfully");


		FunctionCall.addComment(driver, previousIssue_id);
	}


	public static void addComment(WebDriver driver, String prev) throws InterruptedException {


		/* ******** write new comment in issue2 ******* */
		driver.findElement(By.xpath("//textarea[@id='new_comment_field']")).sendKeys("Comment Added to the issue");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,100)");

		System.out.println("New comment added in issue 2");

		/* ******** click on comment button******* */
		driver.findElement(By.xpath("//button[contains(text(),'Comment')]")).click();

		Thread.sleep(2000);
		/* ******** write new comment in issue2 ******* */
		driver.findElement(By.xpath("//textarea[@id='new_comment_field']")).click();

		driver.findElement(By.xpath("//textarea[@id='new_comment_field']")).sendKeys(prev);

		/* ******** click on comment button******* */
		driver.findElement(By.xpath("//button[contains(text(),'Comment')]")).click();

		/* ******** click on 2nd comment of issue 2****** */
		driver.findElement(By.xpath("//a[@class='issue-link js-issue-link']")).click();

		System.out.println("Navigate to the  first issue from the comment");


	}





	public void deleteRepo(WebDriver driver, Properties prop) throws InterruptedException {
		/* ******** Click on repository***** */
		driver.findElement(By.linkText(repoName)).click();
		
		Thread.sleep(2000);
		
		/* ******** Click on settings***** */
		driver.findElement(By.xpath("//span[contains(text(),'Settings')]")).click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,2000)");
		
		
		

		/* ******** wait until Delete this repository button not visible***** */
		WebDriverWait wait =new WebDriverWait(driver, 20);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//summary[contains(text(),'Delete this repository')]"))).click();

		/* ******** Enter username and Repository name***** */
		driver.findElement(By.xpath("//input[@aria-label='Type in the name of the repository to confirm that you want to delete this repository.']")).sendKeys(prop.getProperty("Username")+"/"+repoName);
		
		/* ******** click on "I understand the consequences, delete this repository"***** */
		driver.findElement(By.xpath("//button[contains(text(),'I understand the consequences, delete this reposit')]")).click();

		System.out.println("Repository deleted successfully");
		
		driver.quit();

	}

}


