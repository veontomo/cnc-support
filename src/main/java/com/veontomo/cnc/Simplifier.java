package com.veontomo.cnc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Simplifies given path
 */
public class Simplifier {
	/**
	 * Removes the segments that are already present in the previous paths.
	 * 
	 * @param paths
	 * @return
	 */
	public static Collection<Path> removeDuplicates(Collection<Path> paths) {
		final Set<Segment> seenSegments = new HashSet<>();
		final ArrayList<Path> result = new ArrayList<>(paths.size());
		for (Path path : paths) {
			final int s = path.getSegmentCount();
			LinkedList<Segment> segments = new LinkedList<>();
			for (int segCounter = 0; segCounter < s; segCounter++) {
				final Segment segment = path.getSegment(segCounter);
				if (seenSegments.contains(segment.order())) {
					if (!segments.isEmpty()) {
						result.add(Path.fromSegments(segments));
						segments = new LinkedList<>();
					}
				} else {
					segments.add(segment);
					seenSegments.add(segment.order());
				}
			}
			result.add(Path.fromSegments(segments));
		}
		return result;
	}

}
