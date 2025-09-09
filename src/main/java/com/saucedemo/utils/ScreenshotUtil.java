package com.saucedemo.utils; 

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {

    public static String takeScreenshot(WebDriver driver, String testName) {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String destPath = "reports/screenshot/" + testName + "_" + timestamp + ".png";
        
        try {
            File destFile = new File(destPath);
            destFile.getParentFile().mkdirs(); 
            FileUtils.copyFile(srcFile, destFile);
            System.out.println("Screenshot saved at: " + destFile.getAbsolutePath());
            return destFile.getAbsolutePath(); 
        } catch (IOException e) {
           System.err.println("Failed to save screenshot: " + e.getMessage());
            return null; 
        }
    }
}

