package com.veontomo.cnc;

record Segment(double start, double end) {
	String concat() {
		return String.format("%s,%s", "" + start, "" + end);
	}
}
