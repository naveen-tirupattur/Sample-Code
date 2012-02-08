package my.samples;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * This is a sample to Read an  Excel Sheet using 
 * Jakarta POI API 
 */

public class ReadFromExcel {	

	public static void main(String args[]){       
		try{
			//File to read
			String fileToBeRead="C:/My Workspace/Test/Sample/Test.xls";

			//File to write
			String fileToWrite = "C:/My Workspace/Test/Sample/Output.xls";

			// Create a workbook for reading
			HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(fileToBeRead));

			// Create a workbook for writing
			HSSFWorkbook newWorkBook = new HSSFWorkbook();

			//Get the sheet
			HSSFSheet sheet = workbook.getSheet("Sheet1");

			//Create the sheet
			HSSFSheet newSheet = newWorkBook.createSheet();			

			//Get the rows in the sheet
			Iterator rows  = sheet.rowIterator();

			int rowCount = 0;
			while (rows.hasNext ())
			{
				//Get the row
				HSSFRow row = (HSSFRow)rows.next ();				

				//Create a new row
				HSSFRow newRow = newSheet.createRow((short)rowCount);

				//Get the student number				
				int studentNumber = (int)row.getCell((short)0).getNumericCellValue();

				//Create a Cell to store student number				
				HSSFCell newCell = newRow.createCell((short)0);

				//Set the cell value
				newCell.setCellValue(studentNumber);


				rowCount++;

				//Count the total for cells 
				int total = 0;				
				for(int i = 2;i<5;i++)
				{
					HSSFCell cell = row.getCell((short)i);
					total+=cell.getNumericCellValue();
				}		

				//Create a new cell to store the total
				newCell = newRow.createCell((short)1);
				newCell.setCellValue(total);

				//Create a new cell to store the average
				newCell = newRow.createCell((short)2);
				newCell.setCellValue(total/3);

			}
			try{
	             FileOutputStream out = new FileOutputStream(fileToWrite);
	             newWorkBook.write(out);
	             out.close();
	         }catch(Exception e){ e.printStackTrace();}       
		}
		catch (IOException e)
		{
			e.printStackTrace ();

		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}