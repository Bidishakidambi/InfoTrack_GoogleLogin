package infoTrack.testcase;

import java.util.ArrayList;

public class Outcome {


	private boolean status = false;
	ArrayList<TestScript> script = new ArrayList<TestScript>();
	int size = 0;
	
	public boolean isSuccessful() {
		return this.status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public void setScript(TestScript script) {
		this.script.add(script);
	}
	
	public ArrayList<TestScript> getScript() {
		return this.script;
	}
	
	public int getTotalNumberOfRows(){
		return this.script.get(0).size();
	}

}
