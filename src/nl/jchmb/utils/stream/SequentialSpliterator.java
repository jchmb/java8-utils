package nl.jchmb.utils.stream;

import java.util.Spliterator;

/**
 * SequentialSpliterator convenience class. Implementing classes only need to implement tryAdvance.
 * 
 * @param <T>
 */
public abstract class SequentialSpliterator<T> implements Spliterator<T> {
	@Override
	public Spliterator<T> trySplit() {
		return null;
	}

	@Override
	public long estimateSize() {
		return Long.MAX_VALUE;
	}

	@Override
	public int characteristics() {
		return Spliterator.ORDERED;
	}
}
