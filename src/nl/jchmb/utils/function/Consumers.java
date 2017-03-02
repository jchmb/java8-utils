package nl.jchmb.utils.function;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Consumers {
	public static <T, U> BiConsumer<T, U> lift(Consumer<T> consumer) {
		return (x, y) -> consumer.accept(x); 
	}
	
	public static <T> Consumer<T> nullConsumer() {
		return x -> {};
	}
	
	public static <T> Consumer<T> conditional(Consumer<T> consumer, Predicate<T> predicate) {
		return x -> {
			if (predicate.test(x)) {
				consumer.accept(x);
			}
		};
	}
}
