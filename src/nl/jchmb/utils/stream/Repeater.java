package nl.jchmb.utils.stream;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Repeater {
	public static <T> Stream<T> repeat(T token, int n) {
		return IntStream.range(0, n)
			.mapToObj(i -> token);
	}
}
