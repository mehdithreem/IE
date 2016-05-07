package ir.customs.domain;

public class ArgumentNotFoundException extends Exception {
	private String argumentName;

	public ArgumentNotFoundException(String argumentName) {
		this.argumentName = argumentName;
	}
	
	public String getArgumentName() {
		return argumentName;
	}
}
