package Maven.MavenProjectLearning;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Temp {

	public static void main(String[] args) throws IOException, InterruptedException {

		System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://www.w3schools.com/html/html_tables.asp");

		//Taking screen shot
		File src = driver.getScreenshotAs(OutputType.FILE);
		File file = new File ("drivers/1.jpg");
		FileUtils.copyFile(src, file);

		//Reading data from excel sheet
		XSSFWorkbook wb = new XSSFWorkbook("Excel/createLead.xlsx");
		XSSFSheet sheet = wb.getSheetAt(0);
		int rowCount = sheet.getLastRowNum();
		int coloumnCount = sheet.getRow(0).getLastCellNum();
		System.out.println(sheet.getRow(0).getCell(0));
		//Getting data from excel sheet
		for(int i=0;i<rowCount;i++){
			XSSFRow row = sheet.getRow(i);
			for (int j = 0; j < coloumnCount; j++) {
				XSSFCell cell = row.getCell(j);
				if(cell.getCellType()==1){
					System.out.println(cell.getStringCellValue());
				}else System.out.println(cell.getNumericCellValue());
			}			
		}
		System.out.println("----   Data from web table   -----");
		// Working with Web Table
		WebElement table = driver.findElementByXPath("//div[@class='w3-white w3-padding notranslate']");
		List<WebElement> GetRows = table.findElements(By.tagName("tr"));
		for (int i = 0; i < GetRows.size(); i++) {
			WebElement row = GetRows.get(i);

			List <WebElement> GetCells = row.findElements(By.tagName("td"));
			for (int j = 0; j < GetCells.size(); j++) {
				System.out.println(GetCells.get(j).getText());
			}
		}
		driver.quit();
	}
}
