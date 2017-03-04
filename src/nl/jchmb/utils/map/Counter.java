package nl.jchmb.utils.map;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class Counter<T> {
	private Map<T, AtomicInteger> counts;
	private int total;
	
	public Counter() {
		counts = new HashMap<>();
		total = 0;
	}
	
	public int update(T token) {
		int count = counts.computeIfAbsent(token, t -> new AtomicInteger(0)).incrementAndGet();
		total++;
		return count;
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
	
	public int total() {
		return total;
	}
	
	public void update(Stream<T> tokens) {
		tokens.forEach(this::update);
	}
}
