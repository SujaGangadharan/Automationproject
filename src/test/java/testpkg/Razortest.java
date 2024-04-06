package testpkg;

import org.testng.annotations.Test;

import basepkg.Baseclass;
import pagepkg.Razorpage;

public class Razortest extends Baseclass {
	@Test
	public void test() throws Exception
	{
		Razorpage obj= new Razorpage(driver);
		//obj.registration();
		obj.windowhandling();
		//obj.logoverification();
		//obj.contentverification();
		//obj.titleverification();
		//obj.linkvalidation();
		//obj.screenshot();
		//obj.dropdowncount();
		//obj.datepicker();
		obj.Filetest();
		
	}

}
