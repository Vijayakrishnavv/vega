package com.vega.utility;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
//import org.apache.tools.ant.util.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import net.bytebuddy.implementation.bind.annotation.TargetMethodAnnotationDrivenBinder;

public class Helper {
	
	WebDriver driver;
	
	
	/*
	public static void captureScreenshotOnFailure(WebDriver driver , String getMethodName)
	{
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);	
		try 
		{	
		//	    to get current time and date 
		//		org.openqa.selenium.io.FileHandler.copy(src, new File("./FailureScreenshots/Vega" + getMethodName + "_" + getCurrentDateTime() + ".png"));
			org.openqa.selenium.io.FileHandler.copy(src, new File("./TestReport/FailureScreenshots/" + getMethodName + "_"+".png"));
		} 
		catch (IOException e) 
		{
			
			System.out.println("Not able to take screenshot"+e.getMessage());
		}
	}
	
	
	public static void captureScreenshotOnSuccess(WebDriver driver , String getMethodName)
	{
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try 
		{
			//		to get current time and date 
			//		org.openqa.selenium.io.FileHandler.copy(src, new File("./FailureScreenshots/Vega" + getMethodName + "_" + getCurrentDateTime() + ".png"));
			org.openqa.selenium.io.FileHandler.copy(src, new File("./TestReport/SuccessScreenshots/" + getMethodName +".png"));
		} 
		catch (IOException e) 
		{	
			System.out.println("Not able to take screenshot"+e.getMessage());
		}
	}
	
*/
	
	public static String getCurrentDateTime()
	{
		//Date and time
		//DateFormat customDate = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");
		DateFormat customDate = new SimpleDateFormat("dd_MM_yyyy");
		Date date = new Date();
		return customDate.format(date);
	}
	
	public static String getCurrentDateOfFebOnCurrentYear()
	{
	
	/*	Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		String result = cal.getTime().toString();
		return result;
		*/
		Calendar cal = Calendar.getInstance();

		cal.add(Calendar.MONTH, 1);
		
		

		Date date = cal.getTime();             

		SimpleDateFormat format1 = new SimpleDateFormat("dd-mm-YYYY");          

		String inActiveDate = null;

		try {

		    inActiveDate = format1.format(date);

		    System.out.println(inActiveDate );
		    
		  

		} catch (Exception e1) {

		    // TODO Auto-generated catch block

		    e1.printStackTrace();

		}
		return inActiveDate;		
		
	}
	
	public static String getCurrentDay() {
        //Create a Calendar Object
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        //Get Current Day as a number
        int todayInt = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println("Today Int: " + todayInt + "\n");
        //Integer to String Conversion
        String todayStr = Integer.toString(todayInt);
        System.out.println("Today Str: " + todayStr + "\n");
        return todayStr;
    }
	
	//Get The Current Day plus days. You can change this method based on your needs.
    public static String getCurrentDayPlus(int days) {
        LocalDate currentDate = LocalDate.now();
        int dayOfWeekPlus = currentDate.getDayOfWeek().plus(days).getValue();
        return Integer.toString(dayOfWeekPlus);
    }
    
  //Get The Current Day minus days. You can change this method based on your needs.
    public static String getCurrentDayMinus(int days) {
    	
    	LocalDate date = LocalDate.now();
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String text = date.format(formatters);
        LocalDate parsedDate = LocalDate.parse(text, formatters);

        System.out.println("date: " + date);
        System.out.println("Text format " + text);
        System.out.println("parsedDate: " + parsedDate.format(formatters));
         
       
         int dayOfWeekMinus = date.getDayOfWeek().minus(days).getValue();
          
        String s =  Integer.toString(dayOfWeekMinus); 
        
      //  return Integer.toString(dayOfWeekMinus);
        
      return s;
    }
	

}
