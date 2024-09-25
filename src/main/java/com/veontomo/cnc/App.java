package com.veontomo.cnc;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class App {
	public static void main(String[] args) {
		Collection<Path> paths;
		try {
			paths = Loader.extractPaths(new File(args[0]));
			Collection<Path> withoutDuplicates = Simplifier.removeDuplicates(paths);
			String output = Loader.toSvgFormat(withoutDuplicates);
			Loader.save(output, args[1]);
			System.out.println("Saved the output to file " + args[1]);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}
}
