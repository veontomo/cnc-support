package com.veontomo.cnc;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PathTest {

	@Test
	void when_empty_then_length_0() {
		Path path = Path.from("");
		assertEquals(0, path.getSegmentCount());
	}

	@Test
	void when_three_segments_then_length_3() {
		Path path = Path.from("M 45.859504,97.188886 L 25.363636,107.182602 L 45.859504,122.173175");
		assertEquals(3, path.getSegmentCount());
	}

	@Test
	void when_three_segments_then_first_segment() {
		Path path = Path.from("M 45.859504,97.188886 L 25.363636,107.182602 L 45.859504,122.173175");
		Segment segment = path.getSegment(0);
		assertEquals(45.859504, segment.start());
		assertEquals(97.188886, segment.end());
	}

	@Test
	void when_three_segments_then_second_segment() {
		Path path = Path.from("M 45.859504,97.188886 L 25.363636,107.182602 L 45.859504,122.173175");
		Segment segment1 = path.getSegment(1);
		assertEquals(25.363636, segment1.start());
		assertEquals(107.182602, segment1.end());
	}

	@Test
	void when_three_segments_then_third_segment() {
		Path path = Path.from("M 45.859504,97.188886 L 25.363636,107.182602 L 45.859504,122.173175");
		Segment segment = path.getSegment(2);
		assertEquals(45.859504, segment.start());
		assertEquals(122.173175, segment.end());
	}

}
