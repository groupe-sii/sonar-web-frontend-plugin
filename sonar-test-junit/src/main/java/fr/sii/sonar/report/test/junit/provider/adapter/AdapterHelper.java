package fr.sii.sonar.report.test.junit.provider.adapter;

public class AdapterHelper {
	public static int getInt(String value) {
		return value==null || value.length()==0 ? 0 : Integer.parseInt(value);
	}

	public static long getLong(String value) {
		return value==null || value.length()==0  ? 0 : Long.parseLong(value);
	}

	public static long getDuration(String time) {
		return time==null || time.length()==0 ? 0 : Math.round(Double.parseDouble(time)*1000);
	}
}
