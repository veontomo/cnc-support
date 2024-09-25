package com.veontomo.cnc;

record Segment(Point start, Point end) {
	public Segment order() {
		if (start.x() > end.x()) {
			return new Segment(end, start);
		}
		if (start.x() < end.x()) {
			return new Segment(start, end);
		}
		if (start.y() > end.y()) {
			return new Segment(end, start);
		}
		return new Segment(start, end);
	}

	@Override
	public String toString() {
		return start.toString() + "-" + end.toString();
	}
}