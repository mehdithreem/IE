package ir.customs.tools;

public class IntegerChecker {
	public static Boolean check(String number) {
		try {
			Integer.valueOf(number);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
}
