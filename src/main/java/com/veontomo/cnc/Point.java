package com.veontomo.cnc;

record Point(double x, double y) {
	String concat() {
		return String.format("%s,%s", "" + x, "" + y);
	}

	@Override
	public String toString() {
		return "(" + concat() + ")";
	}
}
