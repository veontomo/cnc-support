package com.veontomo.cnc;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Loader {

	public static Collection<Path> extractPaths(File file)
			throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(file);
		doc.getDocumentElement().normalize();

		NodeList list = doc.getElementsByTagName("path");
		final int size = list.getLength();
		final ArrayList<Path> result = new ArrayList<>(size);
		for (int temp = 0; temp < size; temp++) {
			Node node = list.item(temp);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				String d = element.getAttribute("d");
				final Path path = Path.from(d);
				result.add(path);
			}
		}
		return result;

	}

	public static String toSvgFormat(Collection<Path> paths) {
		final String head = """
				<?xml version="1.0" ?>
				<svg xmlns="http://www.w3.org/2000/svg" xmlns:ev="http://www.w3.org/2001/xml-events" xmlns:xlink="http://www.w3.org/1999/xlink" baseProfile="full" height="527px" version="1.1" viewBox="-56.07900000000001 -49.1982386 670.158 587.5888632" width="600px">
					<defs/>
				""";
		final StringBuilder builder = new StringBuilder(head);
		paths.forEach(p -> {
			builder.append(p.toSvgFormat());
			builder.append("\n");
		});
		builder.append("</svg>");
		return builder.toString();

	}

	public static void save(String output, String file) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		writer.write(output);

		writer.close();

	}

}
