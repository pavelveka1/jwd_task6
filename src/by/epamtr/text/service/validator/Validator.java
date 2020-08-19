package by.epamtr.text.service.validator;

import java.io.File;

public class Validator {

	public static boolean validete(String path) {
		File file=new File(path);
		if(file.exists()) {
			if(file.isFile()) {
				return true;
			}
		}
		return false;
	}
}
