package nl.jchmb.utils.stream.aggregate;

import java.util.function.Predicate;
import java.util.stream.Stream;

public class Proportion {
	public static <T> double measure(Stream<T> stream, Predicate<T> predicate) {
		return stream.mapToDouble(e -> predicate.test(e) ? 1.0d : 0.0d)
				//.average()
				.sum();
				//.getAsDouble();
	}
}
