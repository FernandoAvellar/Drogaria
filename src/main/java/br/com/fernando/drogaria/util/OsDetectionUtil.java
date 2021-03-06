package br.com.fernando.drogaria.util;

import java.util.Locale;

public final class OsDetectionUtil {

	public enum OSType {
		Windows, MacOS, Linux, Other
	};

	// cached result of OS detection
	protected static OSType detectedOS;

	public static OSType getOperatingSystemType() {
		if (detectedOS == null) {
			String OS = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
			if ((OS.indexOf("mac") >= 0) || (OS.indexOf("darwin") >= 0)) {
				detectedOS = OSType.MacOS;
			} else if (OS.indexOf("win") >= 0) {
				detectedOS = OSType.Windows;
			} else if (OS.indexOf("nux") >= 0) {
				detectedOS = OSType.Linux;
			} else {
				detectedOS = OSType.Other;
			}
		}
		return detectedOS;
	}

	public static String configuraCaminhoDiretorio() {
	  
	  if(getOperatingSystemType().toString() == "MacOS") {
		 return "/Users/fernando/Documents/workspace/Drogaria/uploads/";  
	  } else if (getOperatingSystemType().toString() == "Windows") {
		 return "C:/Users/fernando.avellar/workspace/Drogaria/Drogaria/uploads/";  
	  } else {
		 return null;
	  }
 
	}
	
	public static void main(String[] args) {
		System.out.println(configuraCaminhoDiretorio());
	}
	
}
