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
		final Set<Segment> segments = new HashSet<>();
		final ArrayList<Path> result = new ArrayList<>(paths.size());
		for (Path path : paths) {
			final int segmentCount = path.getSegmentCount();
			LinkedList<Segment> revised = new LinkedList<>();
			for (int counter = 0; counter < segmentCount; counter++) {
				final Segment segment = path.getSegment(counter);				
				if (segments.contains(segment)) {
					if(!revised.isEmpty()) {
						result.add(new Path(revised));
						revised = new LinkedList<Segment>();
					}
				} else {
					revised.add(segment);
					segments.add(segment);
				}
				
			}
		}
		return result;
	}

}
