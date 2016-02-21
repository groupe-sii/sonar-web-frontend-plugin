package fr.sii.sonar.report.core.common.util.compat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Sonar changes libraries inclusions... Some libraries like guava are shaded
 * and no more available... In order to prevent big changes in the future do to
 * Sonar dependencies, we use our own utility classes.
 * 
 * <p>
 * Soanr 5.3 removes utility libraries
 * 
 * @author Aurélien Baudet
 */
public class IOUtils {
	/**
	 * Get the contents of an <code>InputStream</code> as a list of Strings, one
	 * entry per line, using the default character encoding of the platform.
	 * <p>
	 * This method buffers the input internally, so there is no need to use a
	 * <code>BufferedInputStream</code>.
	 *
	 * @param stream
	 *            the <code>InputStream</code> to read from, not null
	 * @return the list of Strings, never null
	 * @throws NullPointerException
	 *             if the input is null
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	public static List<String> readLines(InputStream stream) throws IOException {
		try(BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
			List<String> lines = new ArrayList<>();
			String line = reader.readLine();
			while(line!=null) {
				lines.add(line);
				line = reader.readLine();
			}
			return lines;
		}
	}

	/**
	 * Get the contents of an <code>InputStream</code> as a String using the
	 * default character encoding of the platform.
	 * <p>
	 * This method buffers the input internally, so there is no need to use a
	 * <code>BufferedInputStream</code>.
	 * 
	 * @param stream
	 *            the <code>InputStream</code> to read from
	 * @return the requested String
	 * @throws NullPointerException
	 *             if the input is null
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	public static String toString(InputStream stream) throws IOException {
		try(BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
			StringBuilder sb = new StringBuilder();
			String line = reader.readLine();
			while(line!=null) {
				sb.append(line);
				line = reader.readLine();
			}
			return sb.toString();
		}
	}
}
