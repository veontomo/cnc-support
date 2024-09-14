package com.veontomo.cnc;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

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

	public static Collection<Path> extractPaths(File file) throws ParserConfigurationException, SAXException, IOException {
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

}
