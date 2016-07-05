package nl.jchmb.utils.stream;

import java.util.stream.Stream;

@FunctionalInterface
public interface Streamable<T> {
	public Stream<T> stream();
}
