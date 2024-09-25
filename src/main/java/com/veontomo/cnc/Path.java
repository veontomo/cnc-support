package com.veontomo.cnc;

import java.util.LinkedList;
import java.util.stream.Collectors;

public class Path {

	private final LinkedList<Point> points;
	private final int pointCount;
	private final int segmentCount;
	private final LinkedList<Segment> segments;

	private Path(LinkedList<Point> points, LinkedList<Segment> segments) {
		this.points = points;
		this.segments = segments;
		this.pointCount = points.size();
		this.segmentCount = segments.size();
	}

	public static Path fromPoints(LinkedList<Point> points) {
		return new Path(points, calculateSegments(points));
	}

	public static Path fromSegments(LinkedList<Segment> segments) {
		return new Path(calculatePoints(segments), segments);
	}

	private static LinkedList<Point> calculatePoints(LinkedList<Segment> items) {
		final LinkedList<Point> result = new LinkedList<>();
		if (!items.isEmpty()) {
			result.add(items.get(0).start());
			items.forEach(segment -> result.add(segment.end()));
		}
		return result;
	}

	private static LinkedList<Segment> calculateSegments(LinkedList<Point> points) {
		final LinkedList<Segment> result = new LinkedList<>();
		for (int i = 0; i < points.size() - 1; i++) {
			result.add(new Segment(points.get(i), points.get(i + 1)));
		}
		return result;
	}

	public int getSegmentCount() {
		return segmentCount;
	}

	public Segment getSegment(int index) {
		return this.segments.get(index);
	}

	public static Path from(String data) {
		if (data == null || data.isBlank()) {
			return Path.empty();
		}
		final String[] parts = data.split("\\s?[M|L]\\s+");
		final LinkedList<Point> points = new LinkedList<Point>();
		if (parts.length < 2) {
			return Path.empty();
		}
		for (int index = 1; index < parts.length; index++) {
			final String part = parts[index];
			String[] pair = part.split(",");
			if (pair.length != 2) {
				throw new RuntimeException("Exactly two coordinates are expected. Instead at position " + index
						+ " it is detected " + pair.length + "(" + pair + ") in " + data);
			}
			points.add(new Point(Double.parseDouble(pair[0]), Double.parseDouble(pair[1])));
		}
		return Path.fromPoints(points);

	}

	private static Path empty() {
		return new Path(new LinkedList<Point>(), new LinkedList<Segment>());
	}

	public int getPointCount() {
		return this.pointCount;
	}

	public Point getPoint(int index) {
		return this.points.get(index);
	}

	public String toSvgFormat() {
		if (this.points.isEmpty()) {
			return "";
		}
		String template = "<path d=\"M %s\" fill=\"none\" stroke=\"#000000\" stroke-width=\"0.558\"/>";
		String coords = this.points.stream().map(s -> s.concat()).collect(Collectors.joining(" L "));
		return String.format(template, coords);
	}

}
