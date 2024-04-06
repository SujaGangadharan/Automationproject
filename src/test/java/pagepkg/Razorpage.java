package pagepkg;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;

import sun.net.www.protocol.http.HttpURLConnection;

public class Razorpage {
	WebDriver driver;
	By razorsignup=By.xpath("//*[@id=\"root\"]/nav/div/div[2]/a[2]");
	By fullname=By.name("name");
	By organizationname= By.name("brand-name");
	By workemail=By.xpath("//*[@id=\"input-email\"]");
	By phonenumber=By.xpath("//*[@id=\"input-phone-number\"]");
	By nempoyee=By.xpath("//*[@id=\"signUpForm\"]/fieldset/select[1]");
	By title=By.xpath("//*[@id=\"title\"]");
	By privacypolicy=By.xpath("//*[@id=\"legal-consent-check\"]");
	By signup=By.name("signUp");
	By login=By.xpath("//body/div[@id='root']/nav[1]/div[1]/div[2]/a[1]");
	By email=By.name("email");
	By password=By.id("password");
	By loginin=By.name("action");
	By logo=By.xpath("/html/body/header/a/figure/img");
	By contents=By.xpath("/html/body/header/div[3]/div[3]/div/span/img");
	By Screensht=By.xpath("/html/body/header/a/figure");
	By reimbursement=By.xpath("/html/body/div[1]/nav/div/ul[1]/a[5]/li/img[1]");
	By attendance=By.xpath("/html/body/div[1]/nav/div/ul[1]/a[3]");
	By april=By.xpath("//*[@id=\"inlineDatepicker\"]/div/div[2]/div/div/select[1]");
	By next=By.xpath("//*[@id=\"inlineDatepicker\"]/div/div[1]/a[3]");
	By date=By.xpath("//*[@id=\"inlineDatepicker\"]/div/div[2]/div/table/tbody/tr/td/a");
	
	        //--------------constructor-------------//
	public Razorpage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	       //--------------Registration-----------//
	
	public void registration() throws Exception
	{
		Thread.sleep(1000);

		String parentwindow=driver.getWindowHandle();
		System.out.println("parent window title  "+driver.getTitle());
		driver.findElement(razorsignup).click();
		
		 Set<String> allwindowhandles = driver.getWindowHandles();
		 for(String handles:allwindowhandles)
		 {
			 System.out.println(handles);
		 
		 if(!handles.equalsIgnoreCase(parentwindow))
		 {
			 Thread.sleep(1000);
			 driver.switchTo().window(handles);
			 driver.findElement(fullname).sendKeys("Suja G");
				driver.findElement(organizationname).sendKeys("Luminar");
				driver.findElement(workemail).sendKeys("sujagangadharan99@gmail.com");
				driver.findElement(phonenumber).sendKeys("9947827447");
				
	//dropdown//
				
				Thread.sleep(1000);
				WebElement m=driver.findElement(nempoyee);
				Select el=new Select(m) ;
				el.selectByValue("1-5");
				
				WebElement t=driver.findElement(title);
				Select tl=new Select(t);
				tl.selectByIndex(2);
				
				driver.findElement(privacypolicy).click();
				driver.findElement(signup).click();
				
		 }
		 driver.switchTo().window(parentwindow);
		 }
		
		
		
	}
	            //-------------window handling---------------//
	public void windowhandling() throws Exception
	{
		Thread.sleep(5000);
		String parentwindow=driver.getWindowHandle();
		System.out.println("parent window title  "+driver.getTitle());
		driver.findElement(login).click();
		
		 Set<String> allwindowhandles = driver.getWindowHandles();
		 for(String handle:allwindowhandles)
		 {
			 System.out.println(handle);
			 
			 if(!handle.equalsIgnoreCase(parentwindow))
			 {
				 Thread.sleep(5000);
				 driver.switchTo().window(handle);
				 driver.findElement(email).sendKeys("sujaalingal@gmail.com");
				 driver.findElement(password).sendKeys("Suja@123");
				 driver.findElement(loginin).click();
				 
				 System.out.println("login successfull");
			 }
		 }
		 
	}
	//-----------------logo verification--------------//
	
	public void logoverification()
	{
		WebElement log=driver.findElement(logo);
		if(log.isDisplayed())
		{
			System.out.println("Logo is present");
		}
		else
		{
			System.out.println("Logo is not present");
		}
	}
	//--------------------content verification--------------//
	
	public void contentverification() throws Exception
	{
		Thread.sleep(20000);
		String content=driver.getPageSource();
		if(content.contains("Payments"))
		{
			System.out.println("Content verified");
		}
		else
		{
			System.out.println("Content not Verified");
		}
	}
	
	//------------------title verification-------------------//
	
	public void titleverification()
	{
		String actualtitle=driver.getTitle();
		{
			System.out.println("actual title--" +driver.getTitle());
		}
		String expectedtitle="Login | RazorpayX Payroll";
		if(actualtitle.equals(expectedtitle))
		{
			System.out.println("pass");
		}
		else
		{
			System.out.println("fail");
		}

	}
	//----------------Link validation---------------//
	
	public void linkvalidation () throws Exception
	{
	Thread.sleep(5000);
	String baseurl="https://payroll.razorpay.com/dashboard";
		driver.get(baseurl);
		URL ob=new URL(baseurl);
		HttpURLConnection con=(HttpURLConnection)ob.openConnection();
		con.connect();
		
		if(con.getResponseCode()==200)
		{
			System.out.println("Valid Url  :" );
		}
		else
		{
			System.out.println("Invalid Url  :" );
		}
	}
	//-----------------Screenshot---------------//
	
	public void screenshot() throws Exception
	{
		System.out.println("-------Screenshot------");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		FileHandler.copy(src, new File("./screen//page.png"));
	}
     //----------------dropdown count--------------//
	
	public void dropdowncount() throws Exception
	{
		Thread.sleep(5000);
		driver.findElement(reimbursement).click();
		 WebElement dc=driver.findElement(By.xpath("//*[@id=\"type\"]"));
		 
		Select ob=new Select(dc);
		ob.selectByValue("4");
		System.out.println("reimbursement");
		List<WebElement> li1= ob.getOptions();
		System.out.println("count of reimbursement--- "  + li1.size());

	}
	//---------------------datepicker----------------//
	
	public void datepicker() throws Exception
	{
		Thread.sleep(5000);
		driver.findElement(By.xpath("/html/body/div[1]/nav/div/ul[1]/a[3]/li")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		while(true)
		{
		
		WebElement month=driver.findElement(By.xpath("//*[@id=\"inlineDatepicker\"]/div/div[2]/div/div/select[1]"));
				String m1=month.getText();
		if(m1.equals("April"))
		{
			System.out.println(m1);
			break;
		}
		else
		{
			driver.findElement(next).click();
		}
		
		}
		List <WebElement> alldates = (List<WebElement>) driver.findElement(date);
		for(WebElement dateelement:alldates)
		{
			String dt=dateelement.getText();
			System.out.println(dt);
			if(dt.equals("2"))
			{
				System.out.println(dt);
				dateelement.click();
				System.out.println("date selected");
				break;
			}
		}

	}
	//----------------fileupload--------------//
	
	public void Filetest() throws Exception
	{
		Thread.sleep(5000);
		driver.findElement(By.xpath("/html/body/div[1]/nav/div/ul[1]/a[6]/li")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("/html/body/div[1]/main/div[1]/div/form/fieldset/label[4]/strong")).click();
		fileUpload("D:\\Rishi.pdf");
	}
	public void fileUpload(String f) throws AWTException
	{
		
		//to copy path to clipboard
		StringSelection strselection=new StringSelection(f);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(strselection, null);
		//to paste into window
		Robot robot=new Robot();
		robot.delay(5000);
		
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		
		//---------------------scrolldown----------------//
		
		
		JavascriptExecutor js=(JavascriptExecutor) driver;
		
		js.executeScript("window.scrollBy(0,500)","");
		driver.findElement(By.xpath("/html/body/div[1]/nav/div/ul[2]/a[6]/li")).click();
		
		
	}
	
	
	
}


