package infoTrack.testcase;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Dataset {

	String filePath;

	public Dataset(String filePath) {
		this.filePath = filePath;
	}

	public DatasetDetail getData(String datasetName) {
		try {
			FileInputStream filein = new FileInputStream( new File(this.filePath) );
			XSSFWorkbook workbook = new XSSFWorkbook( filein );
			XSSFSheet sheet = workbook.getSheet( datasetName );
			DatasetDetail detail = new DatasetDetail();
			int totalRows = sheet.getLastRowNum()+1;
			int totalColumns = sheet.getRow(0).getLastCellNum();

			DataFormatter df = new DataFormatter();
			XSSFRow firstRow = sheet.getRow(0);
			for(int i = 0; i < totalColumns; i++ ){
				detail.setHeader( df.formatCellValue( firstRow.getCell(i) ));
			}

			for(int j = 1; j < totalRows; j++ ){
				XSSFRow row = sheet.getRow(j);
				ArrayList<String> values = new ArrayList<>();
				for(int i = 0; i < totalColumns; i++ ){
					values.add( df.formatCellValue( row.getCell(i) ));
				}
				detail.setValues( values );
			}
			return detail;

		} catch(Exception ex){
			return new DatasetDetail();
		}

	}

}
