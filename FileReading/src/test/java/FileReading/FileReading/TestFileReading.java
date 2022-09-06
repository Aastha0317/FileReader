package FileReading.FileReading;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestFileReading {
	@Test(dataProvider="MandateEmail")
	public void testExcel(String text) {
		String[] text1=text.split("-");
		String word=text1[0].toString().trim();
		System.out.println(word);
		String secondWord=text1[1].toString().trim();
		String[] meaning=secondWord.split(",");
		for (String string : meaning) {
			System.out.println(string.trim());
		}
		
	}
	

@DataProvider(name = "MandateEmail")
public static Object[][] verifyDumpsterSizePrice() throws FileNotFoundException, IOException {
	return Utilities.Read_Excel("TestData\\TestData.xlsx", "MandateEmail");
}
}