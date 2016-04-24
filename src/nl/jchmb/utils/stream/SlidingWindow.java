package nl.jchmb.utils.stream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * SlidingWindow class.
 * 
 * @param <T>
 */
public class SlidingWindow<T> extends SequentialSpliterator<List<? super T>> {
	private Iterator<T> iterator;
	private int size;
	private List<? super T> buffer; 
	
	private SlidingWindow(Stream<T> stream, int size) {
		iterator = stream.iterator();
		this.size = size;
		buffer = new ArrayList<>(size);
	}

	@Override
	public boolean tryAdvance(Consumer<? super List<? super T>> action) {
		while (buffer.size() < size - 1) {
			if (!iterator.hasNext()) {
				return false;
			}
			buffer.add(iterator.next());
		}
		if (!iterator.hasNext()) {
			return false;
		}
		buffer.add(iterator.next());
		action.accept(Collections.unmodifiableList(buffer));
		buffer.remove(0);
		return true;
	}
	
	/**
	 * Create a sliding window stream of the given stream of the specified size, e.g.,
	 * the stream {0, 1, 2, 3} with size=2 will return the stream of lists {[0, 1], [1, 2], [2, 3]}.
	 * 
	 * @param stream
	 * @param size
	 * @return
	 */
	public static <T> Stream<List<? super T>> of(Stream<T> stream, int size) {
		return StreamSupport.stream(new SlidingWindow<T>(stream, size), false);
	}
}
