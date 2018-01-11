package com.assigntest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDemo 
{

	public static void main(String[] args) 
	{
		XSSFSheet ExcelWSheet;
		 
		XSSFWorkbook ExcelWBook;

		XSSFCell Cell;

		@SuppressWarnings("unused")
		XSSFRow Row;
	
		try 
		{
			System.out.println("IN EXCEL READ...");
			   File fs = new File(System.getProperty("user.dir")+"\\InputData.xlsx");
			   InputStream  ExcelFile = new FileInputStream(fs);
			   ExcelWBook = new XSSFWorkbook(ExcelFile);

			   //ExcelWSheet = ExcelWBook.getSheetAt(0);
			   ExcelWSheet = ExcelWBook.getSheet("TravelTo");
			   XSSFRow row=ExcelWSheet.getRow(0);
			   Cell=row.getCell(0);
			   System.out.println(Cell);  
	}
		catch
		(FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
}
}
