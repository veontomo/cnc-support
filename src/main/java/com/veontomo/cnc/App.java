package com.veontomo.cnc;

import java.util.Collection;

public class App {
	public static void main(String[] args) {
		Collection<Path> paths = Loader.extractPaths(args[0]);
		Collection<Path> withoutDiplicates = Simplifier.removeDuplicates(paths);
	}
}
