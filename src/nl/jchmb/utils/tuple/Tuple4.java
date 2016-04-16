package nl.jchmb.utils.tuple;

public class Tuple4<T1, T2, T3, T4> extends Tuple3<T1, T2, T3> {
	private T4 o4;
	
	public Tuple4(T1 o1, T2 o2, T3 o3, T4 o4) {
		super(o1, o2, o3);
		this.o4 = o4;
	}
	
	public T4 get4() {
		return o4;
	}
}
