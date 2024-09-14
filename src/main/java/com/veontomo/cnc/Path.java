package com.veontomo.cnc;

import java.util.LinkedList;
import java.util.stream.Collectors;

public class Path {

	private final LinkedList<Segment> segments;
	private final int size;

	public Path(LinkedList<Segment> segments) {
		this.segments = segments;
		this.size = this.segments.size();
	}

	public static Path from(String data) {
		if (data == null || data.isBlank()) {
			return Path.empty();
		}
		final String[] parts = data.split("\\s?[M|L]\\s+");
		final LinkedList<Segment> segments = new LinkedList<Segment>();
		if (parts.length < 2) {
			return Path.empty();
		}
		for (int index = 1; index < parts.length; index++) {
			final String part = parts[index];
			String[] pair = part.split(",");
			if (pair.length != 2) {
				throw new RuntimeException("Exactly two coordinates are expected. Instead at position " + index
						+ " it is detected " + pair.length + "(" + pair + ")");
			}
			segments.add(new Segment(Double.parseDouble(pair[0]), Double.parseDouble(pair[1])));
		}
		return new Path(segments);

	}

	private static Path empty() {
		return new Path(new LinkedList<Segment>());
	}

	public int getSegmentCount() {
		return this.size;
	}

	public Segment getSegment(int index) {
		return this.segments.get(index);
	}

	public String toSvgFormat() {
		String template = "<path d=\"M %s\" fill=\"none\" stroke=\"#000000\" stroke-width=\"0.558\"/>";
		String coords = this.segments.stream().map(s -> s.concat()).collect(Collectors.joining("L "));
		return String.format(template, coords);
	}

}
