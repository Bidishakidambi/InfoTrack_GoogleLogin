package infoTrack.testcase;


public enum ActionType {
SET("set"),CLICK("click");

	private String statusCode;
	private ActionType(String s) {
		statusCode =s;
	}public String getStatusCode() {
		return statusCode;
			
	}
}
