package utilities;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


import com.relevantcodes.extentreports.LogStatus;

import base.TestBase;

public class HTTPClientUtils extends TestBase{
	
	public static String GetRequest(String URL , String application_uuid) {
		String responseBody;
		
		RestAssured.baseURI = URL;
		RequestSpecification httpRequest = RestAssured.given();
		httpRequest.header("x-request-id", "a904e7ca-7d73-4322-9269-59ceae5500ff");
		httpRequest.header("x-correlation-id", "a904e7ca-7d73-4322-9269-59ceae5500ff");
		httpRequest.header("Content-Type", "application/json");
		httpRequest.header("Accept-Encoding", "gzip, deflate");
		httpRequest.header("User-Agent", "Mozilla/5.0");
		Response responseFinal = httpRequest.request(Method.GET, application_uuid);
		
		responseBody = responseFinal.getBody().asString();
		return responseBody;
	}
	
	
	public static int generateRandomNumber(int min, int max) {
		return min + (int) (Math.random() * ((max - min) + 1));
	}

}