package fr.sii.sonar.report.core.common.util.compat;

import java.io.IOException;
import java.io.Writer;

/**
 * Sonar changes libraries inclusions... Some libraries like guava are shaded
 * and no more available... In order to prevent big changes in the future do to
 * Sonar dependencies, we use our own utility classes.
 * 
 * <p>
 * Sonar 5.3 removes utility libraries
 * 
 * @author Aurélien Baudet
 */
public class StringEscapeUtils {
	/**
	 * <p>
	 * Escapes the characters in a <code>String</code> using XML entities.
	 * </p>
	 *
	 * <p>
	 * For example: <code>"bread" &amp; "butter"</code> {@literal =>}
	 * <code>&amp;quot;bread&amp;quot; &amp;amp; &amp;quot;butter&amp;quot;</code>.
	 * </p>
	 *
	 * <p>
	 * Supports only the five basic XML entities (gt, lt, quot, amp, apos). Does
	 * not support DTDs or external entities.
	 * </p>
	 *
	 * <p>
	 * Note that unicode characters greater than 0x7f are currently escaped to
	 * their numerical \\u equivalent. This may change in future releases.
	 * </p>
	 *
	 * @param writer
	 *            the writer receiving the unescaped string, not null
	 * @param str
	 *            the <code>String</code> to escape, may be null
	 * @throws IllegalArgumentException
	 *             if the writer is null
	 * @throws IOException
	 *             if there is a problem writing
	 */
	public static void escapeXml(Writer writer, String str) throws IOException {
		if (writer == null) {
			throw new IllegalArgumentException("The Writer must not be null.");
		}
		if (str == null) {
			return;
		}
		Entities.XML.escape(writer, str);
	}

	/**
	 * <p>
	 * Escapes the characters in a <code>String</code> using XML entities.
	 * </p>
	 *
	 * <p>
	 * For example: <code>"bread" &amp; "butter"</code> {@literal =>}
	 * <code>&amp;quot;bread&amp;quot; &amp;amp; &amp;quot;butter&amp;quot;</code>.
	 * </p>
	 *
	 * <p>
	 * Supports only the five basic XML entities (gt, lt, quot, amp, apos). Does
	 * not support DTDs or external entities.
	 * </p>
	 *
	 * <p>
	 * Note that unicode characters greater than 0x7f are currently escaped to
	 * their numerical \\u equivalent. This may change in future releases.
	 * </p>
	 *
	 * @param str
	 *            the <code>String</code> to escape, may be null
	 * @return a new escaped <code>String</code>, <code>null</code> if null
	 *         string input
	 */
	public static String escapeXml(String str) {
		if (str == null) {
			return null;
		}
		return Entities.XML.escape(str);
	}

}
