package util;

public class Validator {

	public static boolean checkNumberEmpty(Double e) {
		return e == null || e.isNaN();
	}

	public static boolean checkStringEmpty(String e) {
		return e == null || e.trim().isEmpty();
	}

}
