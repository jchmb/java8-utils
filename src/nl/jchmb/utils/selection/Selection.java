package nl.jchmb.utils.selection;

import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;

public class Selection {
	public static <T> Stream<T> argMax(Stream<T> stream, Comparator<T> comparator) {
		Optional<T> maxOptional = stream.max(comparator);
		T max;
		if (!maxOptional.isPresent()) {
			return Stream.empty();
		} else {
			max = maxOptional.get();
			return stream.filter(x -> comparator.compare(x, max) == 0);
		}
	}
	
	public static <T> Stream<T> argMin(Stream<T> stream, Comparator<T> comparator) {
		Optional<T> minOptional = stream.min(comparator);
		T min;
		if (!minOptional.isPresent()) {
			return Stream.empty();
		} else {
			min = minOptional.get();
			return stream.filter(x -> comparator.compare(x, min) == 0);
		}
	}
}
