package fr.sii.sonar.report.test.junit.provider.adapter;

public class AdapterHelper {
	public static int getInt(String value) {
		return value==null || value.isEmpty() ? 0 : Integer.parseInt(value);
	}

	public static long getLong(String value) {
		return value==null || value.isEmpty()  ? 0 : Long.parseLong(value);
	}

	public static long getDuration(String time) {
		return time==null || time.isEmpty() ? 0 : Math.round(Double.parseDouble(time)*1000);
	}
}
