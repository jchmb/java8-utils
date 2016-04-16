package nl.jchmb.utils.tuple;

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
}
