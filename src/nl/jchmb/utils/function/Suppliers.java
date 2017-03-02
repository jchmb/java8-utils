package nl.jchmb.utils.function;

import java.util.function.Supplier;

public class Suppliers {
	public static <T> Supplier<T> empty() {
		return () -> null;
	}
}
