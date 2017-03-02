package nl.jchmb.utils.map;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class Counter<T> {
	private Map<T, AtomicInteger> counts;
	
	public Counter() {
		counts = new HashMap<>();
	}
	
	public int update(T token) {
		return counts.computeIfAbsent(token, t -> new AtomicInteger(0)).incrementAndGet();
	}
	
	public int get(T token) {
		return counts.containsKey(token) ? counts.get(token).get() : 0;
	}
	
	public Stream<Map.Entry<T, AtomicInteger>> sortedStream() {
		return counts.entrySet().stream()
			.sorted(Comparator.<Map.Entry<T, AtomicInteger>>comparingInt(e -> e.getValue().get()).reversed());
	}
	
	public Stream<Map.Entry<T, AtomicInteger>> mostCommon(int limit) {
		return sortedStream()
			.limit(limit);
	}
	
	public void update(Stream<T> tokens) {
		tokens.forEach(this::update);
	}
}
