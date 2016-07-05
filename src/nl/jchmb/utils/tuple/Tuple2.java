package nl.jchmb.utils.tuple;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Tuple2<T1, T2> {
	private T1 o1;
	private T2 o2;
	
	public Tuple2(T1 o1, T2 o2) {
		this.o1 = o1;
		this.o2 = o2;
	}
	
	public T1 get1() {
		return o1;
	}
	
	public T2 get2() {
		return o2;
	}
	
	public static <T1, T2> Stream<Tuple2<T1, T2>> lift(T1 key, Stream<T2> stream) {
		return stream.map(obj -> new Tuple2<>(key, obj));
	}
	
	public static <T1, T2> Map<T1, T2> toMap(Stream<Tuple2<T1, T2>> stream) {
		return stream.collect(
				Collectors.toMap(
						tuple -> tuple.get1(),
						tuple -> tuple.get2()
				)
		);
	}
}
