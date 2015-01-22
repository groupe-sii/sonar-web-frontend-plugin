package fr.sii.sonar.report.core.common.provider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.google.common.io.Closeables;

import fr.sii.sonar.report.core.common.domain.Report;
import fr.sii.sonar.report.core.common.exception.ProviderException;

/**
 * Provider that parses an xml file and generates an instance of report class.
 * If the xml raw structure doesn't match report structure, then an adapter can
 * be used to transform raw structure into expected report structure.
 * 
 * @author Aurélien Baudet
 * 
 * @param <R>
 *            the type used for the structure of the generated report
 * @param <T>
 *            the type used for the structure of the raw data
 */
public class XmlFileReportProvider<R extends Report, T> implements Provider<R> {

	/**
	 * The XML stream
	 */
	private InputStream stream;

	/**
	 * The class of the raw data
	 */
	private Class<T> xmlClass;

	/**
	 * An optional adapter to transform raw structure to report structure
	 */
	private ReportAdapter<R, T> adapter;

	/**
	 * Initialize the provider with the xml stream to parse and the class of the
	 * report. In this case, <T> must extends Report
	 * 
	 * @param stream
	 *            the xml stream to parse
	 * @param xmlClass
	 *            the class that will be instantiated and filled with the stream
	 *            content
	 * @throws IllegalArgumentException
	 *             if xmlClass parameter doesn't implement {@link Report}
	 */
	public XmlFileReportProvider(InputStream stream, Class<T> xmlClass) {
		this(stream, checkClass(xmlClass), null);
	}

	/**
	 * Initialize the provider with the xml file to parse and the class of the
	 * report. In this case, <T> must extends Report
	 * 
	 * @param stream
	 *            the xml file to parse
	 * @param xmlClass
	 *            the class that will be instantiated and filled with the stream
	 *            content
	 * @throws IllegalArgumentException
	 *             if xmlClass parameter doesn't implement {@link Report}
	 */
	public XmlFileReportProvider(File reportFile, Class<T> xmlClass) throws FileNotFoundException {
		this(reportFile, checkClass(xmlClass), null);
	}

	/**
	 * Initialize the provider with the xml stream to parse and the class of the
	 * raw xml structure. The adapter must transform the raw structure into the
	 * final report structure.
	 * 
	 * @param stream
	 *            the xml stream to parse
	 * @param xmlClass
	 *            the class that will be instantiated and filled with the stream
	 *            content
	 * @param adapter
	 *            an adapter that transforms the raw structure into the final
	 *            report structure
	 */
	public XmlFileReportProvider(InputStream stream, Class<T> xmlClass, ReportAdapter<R, T> adapter) {
		super();
		this.stream = stream;
		this.xmlClass = xmlClass;
		this.adapter = adapter;
	}

	/**
	 * Initialize the provider with the xml file to parse and the class of the
	 * raw xml structure. The adapter must transform the raw structure into the
	 * final report structure.
	 * 
	 * @param stream
	 *            the xml file to parse
	 * @param xmlClass
	 *            the class that will be instantiated and filled with the stream
	 *            content
	 * @param adapter
	 *            an adapter that transforms the raw structure into the final
	 *            report structure
	 */
	public XmlFileReportProvider(File reportFile, Class<T> xmlClass, ReportAdapter<R, T> adapter) throws FileNotFoundException {
		this(new FileInputStream(reportFile), xmlClass, adapter);
	}

	/**
	 * Utility method used to check if the provided class is allowed or not.
	 * The class is allowed if it implements {@link Report}.
	 * 
	 * @param xmlClass
	 *            the class to check
	 * @return xmlClass
	 * @throws IllegalArgumentException if the class is not allowed.
	 */
	private static <T> Class<T> checkClass(Class<T> xmlClass) {
		if (!Report.class.isAssignableFrom(xmlClass)) {
			throw new IllegalArgumentException("xmlClass must implement " + Report.class.getCanonicalName());
		}
		return xmlClass;
	}

	@SuppressWarnings("unchecked")
	public R get() throws ProviderException {
		try {
			Unmarshaller unmarshaller = JAXBContext.newInstance(xmlClass).createUnmarshaller();
			Object rawStructure = unmarshaller.unmarshal(stream);
			if (adapter != null) {
				return adapter.adapt(xmlClass.cast(rawStructure));
			} else {
				return (R) rawStructure;
			}
		} catch (JAXBException e) {
			throw new ProviderException("failed to parse XML report", e);
		} finally {
			Closeables.closeQuietly(stream);
		}
	}

}
