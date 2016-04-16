package nl.jchmb.utils.stream;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import nl.jchmb.utils.tuple.Tuple2;
import nl.jchmb.utils.tuple.Tuple3;
import nl.jchmb.utils.tuple.Tuple4;

public class Zipper<T, U> extends SequentialSpliterator<Tuple2<T, U>>{
	private Iterator<T> iterator1;
	private Iterator<U> iterator2;
	
	public Zipper(Stream<T> stream1, Stream<U> stream2) {
		iterator1 = stream1.iterator();
		iterator2 = stream2.iterator();
	}
	
	@Override
	public boolean tryAdvance(Consumer<? super Tuple2<T, U>> action) {
		if (!iterator1.hasNext() || !iterator2.hasNext()) {
			return false;
		}
		action.accept(new Tuple2<>(iterator1.next(), iterator2.next()));
		return true;
	}
	
	/**
	 * Zip two streams into a stream of 2-tuples.
	 * 
	 * @param stream1
	 * @param stream2
	 * @return
	 */
	public static <T1, T2> Stream<Tuple2<T1, T2>> zip(Stream<T1> stream1, Stream<T2> stream2) {
		return StreamSupport.stream(new Zipper<>(stream1, stream2), false);
	}
	
	/**
	 * Zip three streams into a stream of 3-tuples.
	 * 
	 * @param stream1
	 * @param stream2
	 * @param stream3
	 * @return
	 */
	public static <T1, T2, T3> Stream<Tuple3<T1, T2, T3>> zip(Stream<T1> stream1, Stream<T2> stream2, Stream<T3> stream3) {
		return zip(zip(stream1, stream2), stream3)
				.map(tuple -> new Tuple3<>(tuple.get1().get1(), tuple.get1().get2(), tuple.get2()));
	}
	
	/**
	 * Zip four streams into a stream of 4-tuples.
	 * 
	 * @param stream1
	 * @param stream2
	 * @param stream3
	 * @param stream4
	 * @return
	 */
	public static <T1, T2, T3, T4> Stream<Tuple4<T1, T2, T3, T4>> zip(Stream<T1> stream1, Stream<T2> stream2, Stream<T3> stream3, Stream<T4> stream4) {
		return zip(zip(stream1, stream2, stream3), stream4)
				.map(tuple -> new Tuple4<>(tuple.get1().get1(), tuple.get1().get2(), tuple.get1().get3(), tuple.get2()));
	}
}
