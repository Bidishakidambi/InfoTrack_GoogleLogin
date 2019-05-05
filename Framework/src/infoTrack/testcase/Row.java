package infoTrack.testcase;

import java.util.ArrayList;

public class Row {
	
	ArrayList<String> row = new ArrayList<String>();
	
	public void addCellData(String cellData) {
		this.row.add(cellData);
	}

	public String getCellData(int index) {
		return this.row.get(index);
	}
	
	public int size(){
		return this.row.size();
	}
}
