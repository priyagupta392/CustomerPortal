package com.util;
import org.testng.annotations.DataProvider;


public class dataProvider {
	
	@DataProvider(name= "companysearchcard" )
	public Object[][] companyName(){
		return new Object[][] {
			{"Capgemini","Capgemini Technology Services"},
			{"Capg","Capgemini Technology Services"},
			{"Capgemini Technology Services","Capgemini Technology Services"}
			
		};
	}

}
