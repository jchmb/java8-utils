package nl.jchmb.utils.tuple;

public class Tuple3<T1, T2, T3> extends Tuple2<T1, T2> {
	private T3 o3;
	
	public Tuple3(T1 o1, T2 o2, T3 o3) {
		super(o1, o2);
		this.o3 = o3;
	}

	public T3 get3() {
		return o3;
	}
}
