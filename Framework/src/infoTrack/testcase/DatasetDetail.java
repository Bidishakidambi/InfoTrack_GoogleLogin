package infoTrack.testcase;

import java.util.ArrayList;

public class DatasetDetail {

	ArrayList<String> header = new ArrayList<>();
	ArrayList<ArrayList<String>> values = new ArrayList<>();
	
	public ArrayList<String> getHeader() {
		return header;
	}
	
	public void setHeader(String header) {
		this.header.add( header );
	}
	
	public ArrayList<ArrayList<String>> getValues() {
		return values;
	}
	
	public void setValues(ArrayList<String> values) {
		this.values.add( values );
	}

}
