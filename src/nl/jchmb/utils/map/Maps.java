package nl.jchmb.utils.map;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Maps {
	public static <K, V> Map.Entry<K, V> entry(K key, V value) {
		return new AbstractMap.SimpleEntry<>(key, value);
	}
	
	public static <K, V> Collector<Map.Entry<K, V>, ?, Map<K, V>> entriesToMap() {
		return Collectors.toMap(e -> e.getKey(), e -> e.getValue());
	}
	
	public static <K, V> MapBuilder<K, V> builder(Map<K, V> map) {
		return new MapBuilder<>(map);
	}
	
	public static <K, V> MapBuilder<K, V> builder() {
		return builder(new HashMap<>());
	}
}
