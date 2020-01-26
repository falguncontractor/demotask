package testcases;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import base.TestBase;

public class test_findError extends TestBase {

	// This test is to get the number of errors in error code
	
	@Test
	public void test_Verify_unique_error_numbers() throws IOException {

		FileInputStream fis_error;
		ArrayList all_error_values = new ArrayList<String>();
		System.out.println("------- TEST Starts: The actual number of unique error log-lines-------");

		try {
			fis_error = new FileInputStream(
					System.getProperty("user.dir") + "\\resource_data\\payload\\application.log");
			BufferedReader br = new BufferedReader(new InputStreamReader(fis_error));
			String sCurrentLine;

			/* read log line by line */
			while ((sCurrentLine = br.readLine()) != null) {
				/* parse strLine to obtain what you want */
				String sysErrorLine = sCurrentLine;

				if (sysErrorLine.matches("(?s).*\\] ERROR\\b.*")) {
					// System.out.println(sysErrorLine);
					String actualErrorvalue = sysErrorLine;
					actualErrorvalue = actualErrorvalue.substring(13);
					all_error_values.add(actualErrorvalue);
				}
			}
			fis_error.close();
			List<String> myUniqueList = (List<String>) all_error_values.stream().distinct()
					.collect(Collectors.toList());
			System.out.println(" The actual number of unique error log-lines is :  " + myUniqueList.size());

			 Reporter.log( "The actual number of unique error log-lines is :  " + myUniqueList.size());

			for (String er : myUniqueList) {
				// System.out.println("-------ERROR-------" + er);
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("**** TEST ENDS ****");

	}

}