package ir.customs.tools;

public class ArgumentNotFoundException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4795181206983782066L;
	private String argumentName;

	public ArgumentNotFoundException(String argumentName) {
		this.argumentName = argumentName;
	}
	
	public String getArgumentName() {
		return argumentName;
	}
}
