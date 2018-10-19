package demo.com.response;

/**
 * @author quoc tran
 *
 */
public class Result {
	private String message = "PASS !";
	private boolean validation;
	private String test;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isValidation() {
		return validation;
	}

	public void setValidation(boolean validation) {
		this.validation = validation;
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	
}
