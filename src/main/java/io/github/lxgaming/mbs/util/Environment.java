package io.github.lxgaming.mbs.util;

public class Environment {
	
	public static String getJavaVendor() {
		return System.getProperty("java.vendor");
	}
	
	public static String getJavaVersion() {
		return System.getProperty("java.version");
	}
	
	public static String getOSArch() {
		return System.getProperty("os.arch");
	}
	
	public static String getOSName() {
		return System.getProperty("os.name");
	}
	
	public static String getOSVersion() {
		return System.getProperty("os.version");
	}
	
	public static String getUserHome() {
		return System.getProperty("user.home");
	}
}