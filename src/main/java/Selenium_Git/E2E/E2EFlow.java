package Selenium_Git.E2E;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;



public class E2EFlow {
	
	 private static WebDriver driver = null;
	 Properties prop = null;
	 
	 

	public static void main(String[] args) throws InterruptedException, IOException {
		String baseUrl = "https://github.com/login";
		String driverPath = "..\\E2E\\Driver\\chromedriver.exe";
		System.out.println("launching chrome browser");
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
		driver.get(baseUrl);       
		driver.manage().window().maximize();	    
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        

        File file = new File("..\\E2E\\Datafiles\\data.properties");
  	  
		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Properties prop = new Properties();
		
		//load properties file
		try {
			prop.load(fileInput);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
       
        
        
		FunctionCall  fc = new FunctionCall();
		fc.login(driver, prop);
		fc.createRepo(driver);
		fc.createIssue(driver);
		fc.createIssue2(driver);
		fc.deleteRepo(driver,prop);

	}

}
