package utility;

import java.io.File;
import java.io.FileInputStream;
//import java.io.FileNotFoundException;
import java.io.IOException;

//import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	
	public static String setExcelFile(String path,int shNum, int row, int col) throws IOException
	{
		
		 File src=new File(path);
		 FileInputStream fis=new FileInputStream(src);

		   XSSFWorkbook wb=new XSSFWorkbook(fis);
		   XSSFSheet sh1= wb.getSheetAt(shNum);
		   wb.close();
		   return  sh1.getRow(row).getCell(col).getStringCellValue();
	}
		  
	public static int lengths(String locate,int shNum) throws IOException
	{
		//System.out.println(locate+"   "+shNum);
		File src=new File(locate);
		 FileInputStream fis=new FileInputStream(src);

		   XSSFWorkbook wb=new XSSFWorkbook(fis);
		   XSSFSheet sh1= wb.getSheetAt(shNum);
		   wb.close();
		   return sh1.getLastRowNum();
	}
}
