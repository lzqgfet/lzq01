package com.lzq.utils;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.lzq.dao.BaseDao;
import com.lzq.pojo.Mobile;
import com.lzq.service.MobileService;
import com.lzq.service.MobileServiceImpl;

public class ExcelToMysql {

	public static void main(String[] args) {
		try {
			Workbook workbook = WorkbookFactory.create(new FileInputStream("d:/lzqwork/Mobile.xls"));
			int sheetnum=workbook.getNumberOfSheets();
			List<Mobile> mlist=new ArrayList<Mobile>();
			for(int i=0;i<sheetnum;i++){
				Sheet sheet = workbook.getSheetAt(i);
				int rownum = sheet.getPhysicalNumberOfRows();
				for(int j=0;j<rownum;j++){
					Mobile m=new Mobile();
					List<Object> list=new ArrayList<Object>();
					Row row = sheet.getRow(j);
					int cellnum = row.getPhysicalNumberOfCells();
					for(int k=0;k<cellnum;k++){
						Cell cell = row.getCell(k);
						//Object object = new Object();
						if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
							cell.getStringCellValue();
						} else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
							cell.getNumericCellValue();
						} else if (cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {
							cell.getBooleanCellValue();
						} else if (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK) {
							
						} else {
							cell.getDateCellValue();
							
						}
						list.add(cell);
					}
					//System.out.println(list);
					String mobilenumber=String.valueOf(list.get(1)) ;
					String mobilearea=String.valueOf(list.get(2));
					String mobiletype=String.valueOf(list.get(3));
					String areacode=String.valueOf(list.get(4));
					String postcode=String.valueOf(list.get(5));
					m.setMobileNumber(mobilenumber);
					m.setMobileArea(mobilearea);
					m.setMobileType(mobiletype);
					m.setAreaCode(areacode);
					m.setPostCode(postcode);
					MobileService mobileService=new MobileServiceImpl();
					//mobileService.insert(m);
					/*String sql="insert into mobile(MobileNumber,MobileArea,MobileType,AreaCode,PostCode) values(\"+mobilenumber+\"+'"+mobilearea+"'+'"+mobiletype+"'+'"+areacode+"'+'"+postcode+"')";
					BaseDao.executeUpdate(sql);*/
					System.out.println(mobilenumber+"-"+mobiletype+"-"+mobilearea+"-"+areacode+"-"+postcode);
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	

}
